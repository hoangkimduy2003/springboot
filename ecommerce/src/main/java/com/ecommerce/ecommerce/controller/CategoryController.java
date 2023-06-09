package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public List<CategoryDTO> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable("id") Long id){
        return categoryService.getById(id);
    }

    @PostMapping("")
    public CategoryDTO create(@RequestBody CategoryDTO categoryDTO){
        return categoryService.create(categoryDTO);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@RequestBody CategoryDTO categoryDTO,
                          @PathVariable("id") Long id){
        categoryDTO.setId(id);
        return categoryService.update(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable("id") Long id){
        categoryService.delete(id);
    }

}
