package com.project2.controller;

import com.project2.dto.CourseDTO;
import com.project2.dto.ReponseDTO;
import com.project2.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping("")
    public ReponseDTO<List<CourseDTO>> getAll(){
        return ReponseDTO.<List<CourseDTO>>builder()
                .data(courseService.getAll().getData())
                .status(200)
                .msg("Get all course success")
                .build();
    }

    @PostMapping("")
    public ReponseDTO<CourseDTO> create(@RequestBody CourseDTO courseDTO){
        courseService.create(courseDTO);
        return ReponseDTO.<CourseDTO>builder()
                .data(courseDTO)
                .status(200)
                .msg("Create course success")
                .build();
    }

    @PutMapping("/{id}")
    public ReponseDTO<CourseDTO> update(@RequestBody CourseDTO courseDTO,
                                        @PathVariable("id") Integer id){
        courseDTO.setId(id);
        courseService.update(courseDTO);
        return ReponseDTO.<CourseDTO>builder()
                .data(courseDTO)
                .msg("Update success")
                .status(200)
                .build();
    }
    @DeleteMapping("/{id}")
    public ReponseDTO<Void> delete(@PathVariable("id") Integer id){
        courseService.delete(id);
        return ReponseDTO.<Void>builder()
                .msg("Delete success")
                .status(200)
                .build();
    }
}
