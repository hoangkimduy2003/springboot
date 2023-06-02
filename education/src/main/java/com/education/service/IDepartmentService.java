package com.education.service;

import com.education.dto.DepartmentDTO;
import com.education.entity.Department;
import com.education.reponsitory.DepartmentReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface IDepartmentService {

    DepartmentDTO convertToDto(Department department);

    Department convertToEntity(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAll();

    void delete(Integer id);

    DepartmentDTO getById(Integer id);

    void create(DepartmentDTO departmentDTO);

    void update(DepartmentDTO departmentDTO);

    @Service
    class DepartmentService implements IDepartmentService {
        @Autowired
        private DepartmentReponsitory departmentRepo;

        @Override
        public DepartmentDTO convertToDto(Department department) {
            return new ModelMapper().map(department, DepartmentDTO.class);
        }

        @Override
        public Department convertToEntity(DepartmentDTO departmentDTO) {
            return new ModelMapper().map(departmentDTO, Department.class);
        }

        @Override
        public List<DepartmentDTO> getAll() {
            return departmentRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public void delete(Integer id) {
            departmentRepo.deleteById(id);
        }

        @Override
        public DepartmentDTO getById(Integer id) {
            return convertToDto(departmentRepo.findById(id).orElse(null));
        }

        @Override
        public void create(DepartmentDTO departmentDTO) {
            departmentRepo.save(convertToEntity(departmentDTO));
        }

        @Override
        public void update(DepartmentDTO departmentDTO) {
            Department departmentEntity = departmentRepo.findById(departmentDTO.getId()).orElse(null);
            if(departmentEntity != null){
                departmentEntity = convertToEntity(departmentDTO);
            }
            departmentRepo.save(departmentEntity);
        }
    }
}
