package com.mini.controller;

import com.mini.dto.CategoryDTO;
import com.mini.dto.ResponseDTO;
import com.mini.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public ResponseDTO<List<CategoryDTO>> getAll() {
        return ResponseDTO.<List<CategoryDTO>>builder()
                .data(categoryService.getAll())
                .msg("Get all success status")
                .status(200)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDTO<CategoryDTO> getById(@PathVariable("id") Long id) {
        return ResponseDTO.<CategoryDTO>builder()
                .status(200)
                .msg("Get success")
                .data(categoryService.getById(id))
                .build();
    }

    @PostMapping("")
    public ResponseDTO<CategoryDTO> create(@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryService.create(categoryDTO);
        return ResponseDTO.<CategoryDTO>builder()
                .status(200)
                .data(categoryDTO)
                .msg("Craete status success")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<CategoryDTO> update(@RequestBody @Valid CategoryDTO categoryDTO,
                                         @PathVariable("id") Long id) {
        categoryDTO.setId(id);
        categoryService.update(categoryDTO);
        return ResponseDTO.<CategoryDTO>builder()
                .data(categoryDTO)
                .msg("Update success")
                .status(200)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseDTO.<Void>builder()
                .msg("Update success")
                .status(200)
                .build();
    }
}
