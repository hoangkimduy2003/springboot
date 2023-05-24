package com.project2.controller;

import com.project2.dto.DepartmentDTO;
import com.project2.dto.PageDTO;
import com.project2.dto.ReponseDTO;
import com.project2.reponsitory.DepartmentReponsitory;
import com.project2.service.iplm.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("")
    public ReponseDTO<PageDTO<List<DepartmentDTO>>> getAll(){
        return ReponseDTO.<PageDTO<List<DepartmentDTO>>>builder()
                .status(200)
                .data(departmentService.getAll())
                .build();
    }

    @PostMapping("")
    public ReponseDTO<DepartmentDTO> createDepartment(
            @RequestBody @Valid DepartmentDTO department
    ){
        departmentService.create(department);
        return ReponseDTO.<DepartmentDTO>builder()
                .status(200)
                .msg("Thêm thành công")
                .build();
    }

}
