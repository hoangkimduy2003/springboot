package com.education.service;

import com.education.dto.CourseDTO;
import com.education.entity.Course;
import com.education.reponsitory.CourseReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface ICourseService {
    CourseDTO convertToDto(Course course);

    Course convertToEntity(CourseDTO courseDTO);

    List<CourseDTO> getAll();

    void delete(Integer id);

    CourseDTO getById(Integer id);

    void create(CourseDTO courseDTO);

    CourseDTO update(CourseDTO courseDTO);

    @Service
    class CourseService implements ICourseService {
        @Autowired
        private CourseReponsitory courseRepo;

        @Override
        public CourseDTO convertToDto(Course course) {
            return new ModelMapper().map(course, CourseDTO.class);
        }

        @Override
        public Course convertToEntity(CourseDTO courseDTO) {
            return new ModelMapper().map(courseDTO, Course.class);
        }

        @Override
        @Cacheable(cacheNames = "listCourse")
        public List<CourseDTO> getAll() {
            return courseRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        @Caching(
                evict = {
                        @CacheEvict(cacheNames = "listCourse", allEntries = true),
                        @CacheEvict(cacheNames = "course", key = "#id")
                }
        )
        public void delete(Integer id) {
            courseRepo.deleteById(id);
        }

        @Override
        @Cacheable(cacheNames = "course", key = "#id", unless = "#result == null")
        public CourseDTO getById(Integer id) {
            return convertToDto(courseRepo.findById(id).orElse(null));
        }

        @Override
        @CacheEvict(cacheNames = "listCourse", allEntries = true)
        public void create(CourseDTO courseDTO) {
            courseRepo.save(convertToEntity(courseDTO));
        }

        @Override
        @Caching(
                evict = {
                        @CacheEvict(cacheNames = "listCourse", allEntries = true),
                },
                put = {
                        @CachePut(cacheNames = "course", key = "#courseDTO.id")
                }
        )
        public CourseDTO update(CourseDTO courseDTO) {
            Course courseEntity = courseRepo.findById(courseDTO.getId()).orElse(null);
            if (courseEntity != null) {
                courseEntity = convertToEntity(courseDTO);
            }
            courseRepo.save(courseEntity);
            return courseDTO;
        }
    }
}
