package com.education.controller;

import com.education.dto.DepartmentDTO;
import com.education.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("")
    public List<DepartmentDTO> getAll() {
        return departmentService.getAll();
    }
    @GetMapping("/{id}")
    public DepartmentDTO getById(@PathVariable("id") Integer id) {
        return departmentService.getById(id);
    }
    @PostMapping("")
    public DepartmentDTO create(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.create(departmentDTO);
        return departmentDTO;
    }

    @PutMapping("/{id}")
    public DepartmentDTO update(@RequestBody DepartmentDTO departmentDTO,
                                @PathVariable("id") Integer id) {
        departmentDTO.setId(id);
        departmentService.create(departmentDTO);
        return departmentDTO;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        departmentService.delete(id);
        return "Xoá thành công";
    }
}
