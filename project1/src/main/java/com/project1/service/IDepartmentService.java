package com.project1.service;

import com.project1.dto.DepartmentDTO;
import com.project1.dto.PageDTO;
import com.project1.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IDepartmentService extends IObjectService<DepartmentDTO> {
    PageDTO<List<DepartmentDTO>> searchByKeyWordName( String keyword, int page);
}
