package com.education.controller;

import com.education.dto.CourseDTO;
import com.education.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @GetMapping("")
    public List<CourseDTO> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable("id") Integer id) {
        return courseService.getById(id);
    }

    @PostMapping("")
    public CourseDTO create(@RequestBody CourseDTO courseDTO) {
        courseService.create(courseDTO);
        return courseDTO;
    }

    @PutMapping("/{id}")
    public CourseDTO update(@RequestBody CourseDTO courseDTO,
                            @PathVariable("id") Integer id) {
        courseDTO.setId(id);
        courseService.update(courseDTO);
        return courseDTO;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        courseService.delete(id);
        return "Delete course success";
    }

}
