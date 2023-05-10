package com.project1.service;

import com.project1.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IObjectService<T> {
    PageDTO<List<T>> getAll(int page);
    void delete(Integer id);
    void update(T t);
    void create(T t);
    T getById(Integer id);
}
