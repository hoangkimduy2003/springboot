package com.project2.controller;

import com.project2.dto.PageDTO;
import com.project2.dto.ReponseDTO;
import com.project2.dto.StudentDTO;
import com.project2.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public ReponseDTO<List<StudentDTO>> getAll() {
        return ReponseDTO.<List<StudentDTO>>builder()
                .data(studentService.getAll().getData())
                .status(200)
                .build();
    }


    @PostMapping("")
    public ReponseDTO<StudentDTO> create(@RequestBody StudentDTO studentDTO){
        studentService.create(studentDTO);
        return ReponseDTO.<StudentDTO>builder()
                .data(studentDTO)
                .msg("Thêm thành công")
                .status(200)
                .build();
    }

    @PutMapping("/{id}")
    public ReponseDTO<StudentDTO> update(@RequestBody StudentDTO studentDTO,
                                         @PathVariable("id") Integer id){
        studentDTO.getUser().setId(id);
        studentService.update(studentDTO);
        return ReponseDTO.<StudentDTO>builder()
                .data(studentDTO)
                .msg("Cập nhật thành công")
                .status(200)
                .build();
    }

    @DeleteMapping("/{id}")
    public ReponseDTO<Void> delete(@PathVariable("id") Integer id){
        studentService.delete(id);
        return ReponseDTO.<Void>builder()
                .msg("Xoá thành công")
                .status(200)
                .build();
    }

}
