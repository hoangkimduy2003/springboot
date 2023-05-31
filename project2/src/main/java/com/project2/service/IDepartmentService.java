package com.project2.service;

import com.project2.dto.DepartmentDTO;
import com.project2.dto.PageDTO;
import com.project2.dto.ReponseDTO;
import com.project2.entity.Department;

import java.util.List;

public interface IDepartmentService {
    DepartmentDTO convertToDto(Department department);
    Department convertToEntity(DepartmentDTO departmentDTO);
    PageDTO<List<DepartmentDTO>> getAll();
    DepartmentDTO findById(Integer id);
    void create(DepartmentDTO departmentDTO);
    DepartmentDTO update(DepartmentDTO departmentDTO);
    void delete(Integer id);
}
