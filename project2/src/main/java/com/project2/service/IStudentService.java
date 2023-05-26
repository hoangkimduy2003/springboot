package com.project2.service;

import com.project2.dto.PageDTO;
import com.project2.dto.StudentDTO;
import com.project2.entity.Student;
import com.project2.reponsitory.StudentReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public interface IStudentService {

    StudentDTO convertToDto(Student student);

    Student convertToEntity(StudentDTO studentDTO);

    PageDTO<List<StudentDTO>> getAll();

    void create(StudentDTO studentDTO);

    void update(StudentDTO studentDTO);

    void delete(Integer id);

    StudentDTO getById(Integer id);

    @Service
    public class StudentService implements IStudentService {

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
        public PageDTO<List<StudentDTO>> getAll() {
            PageDTO<List<StudentDTO>> pageDTO = new PageDTO<>();
            Page<Student> pageEntity = studentRepo.findAll(PageRequest.of(0, 150));

            List<StudentDTO> list = pageEntity.get().map(u -> convertToDto(u))
                    .collect(Collectors.toList());
            return PageDTO.<List<StudentDTO>>builder()
                    .totalPages(pageEntity.getTotalPages())
                    .data(list)
                    .totalElements(pageEntity.getTotalElements())
                    .build();

        }

        @Override
        @Transactional
        public void create(StudentDTO studentDTO) {
            studentRepo.save(convertToEntity(studentDTO));
        }

        @Override
        public void update(StudentDTO studentDTO) {
            Student student = studentRepo.findById(studentDTO
                    .getUser().getId()).orElse(null);
            if(student != null){
                    student = convertToEntity(studentDTO);
                    student.setId(student.getUser().getId());
            }
            studentRepo.save(student);
        }

        @Override
        @Transactional
        public void delete(Integer id) {
            studentRepo.deleteById(id);
        }

        @Override
        public StudentDTO getById(Integer id) {
            return convertToDto(studentRepo.findById(id).orElse(null));
        }
    }
}
