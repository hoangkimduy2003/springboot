package com.project2.service.iplm;

import com.project2.dto.DepartmentDTO;
import com.project2.dto.PageDTO;
import com.project2.dto.ReponseDTO;
import com.project2.entity.Department;
import com.project2.reponsitory.DepartmentReponsitory;
import com.project2.service.IDepartmentService;
import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentReponsitory departmentRepo;
    @Override
    public DepartmentDTO convertToDto(Department department) {
        return new ModelMapper().map(department,DepartmentDTO.class);
    }

    @Override
    public Department convertToEntity(DepartmentDTO departmentDTO) {
        return new ModelMapper().map(departmentDTO,Department.class);
    }

    @Override
    public PageDTO<List<DepartmentDTO>> getAll() {
        Page<Department> pageEntity = departmentRepo.findAll(PageRequest.of(0,50));
        List<DepartmentDTO> list =
                pageEntity.get().map(u -> convertToDto(u)).collect(Collectors.toList());
        return PageDTO.<List<DepartmentDTO>>builder()
                .data(list)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();

    }

    @Override
    public DepartmentDTO findById(Integer id) {
        return convertToDto(departmentRepo.findById(id).orElseThrow(NoResultException::new));
    }

    @Override
    public void create(DepartmentDTO departmentDTO) {
        departmentRepo.save(convertToEntity(departmentDTO));
    }

    @Override
    public void update(DepartmentDTO departmentDTO) {
        Department department = departmentRepo.findById(departmentDTO.getId())
                .orElse(null);
        if(department != null){
            department = convertToEntity(departmentDTO);
        }
        departmentRepo.save(department);
    }

    @Override
    public void delete(Integer id) {
        departmentRepo.deleteById(id);
    }


}
