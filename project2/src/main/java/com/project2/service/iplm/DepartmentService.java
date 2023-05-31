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
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentReponsitory departmentRepo;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public DepartmentDTO convertToDto(Department department) {
        return new ModelMapper().map(department, DepartmentDTO.class);
    }

    @Override
    public Department convertToEntity(DepartmentDTO departmentDTO) {
        return new ModelMapper().map(departmentDTO, Department.class);
    }

    @Override
    @Cacheable(cacheNames = "getAll")
    public PageDTO<List<DepartmentDTO>> getAll() {
        Page<Department> pageEntity = departmentRepo.findAll(PageRequest.of(0, 50));
        List<DepartmentDTO> list =
                pageEntity.get().map(u -> convertToDto(u)).collect(Collectors.toList());
        return PageDTO.<List<DepartmentDTO>>builder()
                .data(list)
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .build();

    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "department", key = "#id",
            unless = "#result == null")
    public DepartmentDTO findById(Integer id) {
        return convertToDto(departmentRepo.findById(id).orElseThrow(NoResultException::new));
    }

    @Override
    @Transactional
    @Caching(
            evict = @CacheEvict(cacheNames = "getAll")
    )
    public void create(DepartmentDTO departmentDTO) {
        departmentRepo.save(convertToEntity(departmentDTO));
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = "getAll", allEntries = true)
            },put = {
                    @CachePut(cacheNames = "department",key = "#departmentDTO.id")
    }
    )
    public DepartmentDTO update(DepartmentDTO departmentDTO) {
        Department department = departmentRepo.findById(departmentDTO.getId())
                .orElse(null);
        if (department != null) {
            department = convertToEntity(departmentDTO);
        }
        departmentRepo.save(department);
        return convertToDto(department);
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "department", key = "#id"),
            @CacheEvict(cacheNames = "getAll", allEntries = true)}
    )
    public void delete(Integer id) {
        departmentRepo.deleteById(id);
    }


}
