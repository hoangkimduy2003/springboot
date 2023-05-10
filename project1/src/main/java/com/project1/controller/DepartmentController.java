package com.project1.controller;

import com.project1.dto.DepartmentDTO;
import com.project1.service.impl.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/viewInput")
    public String viewInput(){
        return "department";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute DepartmentDTO departmentDTO){
        departmentService.create(departmentDTO);
        return "redirect:/viewDepartment";
    }
}
