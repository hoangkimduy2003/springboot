package com.mini.service;

import com.mini.dto.CategoryDTO;
import com.mini.entity.Category;
import com.mini.reponsitory.CategoryReponsitory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface ICategoryService {

    CategoryDTO convertToDto(Category category);
    Category converToEntity(CategoryDTO categoryDTO);
    List<CategoryDTO> getAll();
    CategoryDTO getById(Long id);
    void create(CategoryDTO categoryDTO);
    void update(CategoryDTO categoryDTO);
    void delete(Long id);

    @Service
    class CategoryService implements ICategoryService{
        @Autowired
        private CategoryReponsitory categoryRepo;

        @Override
        public CategoryDTO convertToDto(Category brand) {
            return new ModelMapper().map(brand,CategoryDTO.class);
        }

        @Override
        public Category converToEntity(CategoryDTO brandDTO) {
            return new ModelMapper().map(brandDTO,Category.class);
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
        public void create(CategoryDTO categoryDTO) {
            categoryRepo.save(converToEntity(categoryDTO));
        }

        @Override
        public void update(CategoryDTO categoryDTO) {
            Category category = categoryRepo.findById(categoryDTO.getId()).orElse(null);
            if(category != null){
                category = converToEntity(categoryDTO);
            }
            categoryRepo.save(category);
        }

        @Override
        public void delete(Long id) {
            categoryRepo.deleteById(id);
        }
    }
}
