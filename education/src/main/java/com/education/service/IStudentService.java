package com.education.service;

import com.education.dto.StudentDTO;
import com.education.entity.Student;
import com.education.reponsitory.StudentReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IStudentService {
    StudentDTO convertToDto(Student student);

    Student convertToEntity(StudentDTO studentDTO);

    List<StudentDTO> getAll();

    void delete(Integer id);

    StudentDTO getById(Integer id);

    void create(StudentDTO studentDTO);

    void update(StudentDTO studentDTO);

    @Service
    class StudentService implements IStudentService {

        @Autowired
        private StudentReponsitory studentRepo;


        @Override
        public StudentDTO convertToDto(Student student) {
            return new ModelMapper().map(student, StudentDTO.class);
        }

        @Override
        public Student convertToEntity(StudentDTO studentDTO) {
            return new ModelMapper().map(studentDTO, Student.class);
        }

        @Override
        public List<StudentDTO> getAll() {
            return studentRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public void delete(Integer id) {
            studentRepo.deleteById(id);
        }

        @Override
        public StudentDTO getById(Integer id) {
            return convertToDto(studentRepo.findById(id).orElse(null));
        }

        @Override
        public void create(StudentDTO studentDTO) {
            studentRepo.save(convertToEntity(studentDTO));
        }

        @Override
        public void update(StudentDTO studentDTO) {
            Student studentEntity = studentRepo.findById(studentDTO.getId()).orElse(null);
            if(studentEntity != null){
                studentEntity = convertToEntity(studentDTO);
            }
            studentRepo.save(studentEntity);
        }
    }
}
