package com.project2.service;

import com.project2.dto.CourseDTO;
import com.project2.dto.PageDTO;
import com.project2.entity.Course;
import com.project2.reponsitory.CourseReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface ICourseService {
    CourseDTO convertToDto(Course course);
    Course convertToEntity(CourseDTO courseDTO);
    PageDTO<List<CourseDTO>> getAll();
    void create(CourseDTO courseDTO);
    void update(CourseDTO courseDTO);
    void delete(Integer id);
    CourseDTO getById(Integer id);


    @Service
    class CourseService implements ICourseService {
        @Autowired
        private CourseReponsitory courseRepo;

        @Override
        public CourseDTO convertToDto(Course course) {
            return new ModelMapper().map(course,CourseDTO.class);
        }

        @Override
        public Course convertToEntity(CourseDTO courseDTO) {
            return new ModelMapper().map(courseDTO,Course.class);
        }

        @Override
        public PageDTO<List<CourseDTO>> getAll() {
            Page<Course> pageEntity = courseRepo.findAll(PageRequest.of(0,150));
            List<CourseDTO> list = pageEntity.get().map(u -> convertToDto(u))
                    .collect(Collectors.toList());
            return PageDTO.<List<CourseDTO>>builder()
                    .data(list)
                    .totalElements(pageEntity.getTotalElements())
                    .totalPages(pageEntity.getTotalPages())
                    .build();
        }

        @Override
        public void create(CourseDTO courseDTO) {
            courseRepo.save(convertToEntity(courseDTO));
        }

        @Override
        public void update(CourseDTO courseDTO) {
            Course course = courseRepo.findById(courseDTO.getId()).orElse(null);
            if(course != null){
                course = convertToEntity(courseDTO);
            }
            courseRepo.save(course);
        }

        @Override
        public void delete(Integer id) {
            courseRepo.deleteById(id);
        }

        @Override
        public CourseDTO getById(Integer id) {
            return convertToDto(courseRepo.findById(id).orElse(null));
        }
    }
}
