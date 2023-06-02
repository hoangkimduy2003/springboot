package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.reponsitory.CategoryReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface ICategoryService {

    CategoryDTO convertToDto(Category category);

    Category convertToEntity(CategoryDTO categoryDTO);

    List<CategoryDTO> getAll();

    CategoryDTO getById(Long id);

    CategoryDTO create(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO);

    void delete(Long id);

    @Service
    class CategoryService implements ICategoryService {

        @Autowired
        private CategoryReponsitory categoryRepo;

        @Override
        public CategoryDTO convertToDto(Category category) {
            return new ModelMapper().map(category, CategoryDTO.class);
        }

        @Override
        public Category convertToEntity(CategoryDTO categoryDTO) {
            return new ModelMapper().map(categoryDTO, Category.class);
        }

        @Override
        public List<CategoryDTO> getAll() {
            return categoryRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
        }

        @Override
        public CategoryDTO getById(Long id) {
            return convertToDto(categoryRepo.findById(id).orElse(null));
        }

        @Override
        public CategoryDTO create(CategoryDTO categoryDTO) {
            categoryRepo.save(convertToEntity(categoryDTO));
            return categoryDTO;
        }

        @Override
        public CategoryDTO update(CategoryDTO categoryDTO) {
            Category category = categoryRepo.findById(categoryDTO.getId()).orElse(null);
            if (category != null) {
                category = convertToEntity(categoryDTO);
            }
            categoryRepo.save(category);
            return categoryDTO;
        }

        @Override
        public void delete(Long id) {
            categoryRepo.deleteById(id);
        }
    }
}
