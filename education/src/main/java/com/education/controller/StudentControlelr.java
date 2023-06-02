package com.education.controller;

import com.education.dto.StudentDTO;
import com.education.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentControlelr {

    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public List<StudentDTO> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable("id") Integer id) {
        return studentService.getById(id);
    }

    @PostMapping("")
    public StudentDTO create(@RequestBody StudentDTO studentDTO) {
        studentService.create(studentDTO);
        return studentDTO;
    }

    @PutMapping("/{id}")
    public StudentDTO update(@RequestBody StudentDTO studentDTO,
                             @PathVariable("id") Integer id) {
        studentDTO.setId(id);
        studentService.update(studentDTO);
        return studentDTO;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return "Delete student success";
    }

}
