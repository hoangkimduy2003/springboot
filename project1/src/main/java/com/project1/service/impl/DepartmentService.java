package com.project1.service.impl;

import com.project1.dto.DepartmentDTO;
import com.project1.dto.PageDTO;
import com.project1.entity.Department;
import com.project1.reponsitory.DepartmentReponsitory;
import com.project1.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private DepartmentReponsitory departmentReponsitory;

    public Department convertEntity(DepartmentDTO departmentDTO){
        return new ModelMapper().map(departmentDTO,Department.class);
    }
    public DepartmentDTO convertDTO(Department department){
        return new ModelMapper().map(department,DepartmentDTO.class);
    }

    @Override
    public PageDTO<List<DepartmentDTO>> getAll(int page) {
        PageDTO<List<DepartmentDTO>> pageDTO = new PageDTO<>();
        Page<Department> pageEntity = departmentReponsitory.findAll(PageRequest.of(page,5));

        pageDTO.setTotalPages(pageEntity.getTotalPages());
        pageDTO.setTotalElements(pageEntity.getTotalElements());

        pageDTO.setData(pageEntity.get().map(u -> convertDTO(u))
                .collect(Collectors.toList()));
        return pageDTO;
    }

    @Override
    public void delete(Integer id) {
        departmentReponsitory.deleteById(id);
    }

    @Override
    public void update(DepartmentDTO departmentDTO) {
        Department department = departmentReponsitory.findById(departmentDTO.getId()).orElse(null);
        if(department != null){
            department = convertEntity(departmentDTO);
        }
        departmentReponsitory.save(department);
    }

    @Override
    public void create(DepartmentDTO departmentDTO) {
        departmentReponsitory.save(convertEntity(departmentDTO));
    }

    @Override
    public DepartmentDTO getById(Integer id) {
        return convertDTO(departmentReponsitory.findById(id).orElse(null));
    }

    @Override
    public PageDTO<List<DepartmentDTO>> searchByKeyWordName(String keyword, int page) {
        PageDTO<List<DepartmentDTO>> pageDTO = new PageDTO<>();
        Page<Department> pageEntity = departmentReponsitory.searchByKeyWordName("%"+keyword+"%",PageRequest.of(page,5));

        pageDTO.setTotalPages(pageEntity.getTotalPages());
        pageDTO.setTotalElements(pageEntity.getTotalElements());

        List<DepartmentDTO> list = pageEntity.get().map(u -> convertDTO(u)).collect(Collectors.toList());

        pageDTO.setData(list);
        return pageDTO;
    }
}
