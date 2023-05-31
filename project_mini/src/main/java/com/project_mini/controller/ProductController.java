package com.project_mini.controller;

import com.project_mini.dto.ProductDTO;
import com.project_mini.dto.ResponseDTO;
import com.project_mini.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public ResponseDTO<List<ProductDTO>> getAll() {
        return ResponseDTO
                .<List<ProductDTO>>builder()
                .data(productService.getAll())
                .msg("Ôn áp")
                .status(200)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDTO<ProductDTO> getById(@PathVariable("id") Integer id) {
        return ResponseDTO.<ProductDTO>builder()
                .data(productService.getById(id))
                .status(200)
                .msg("Get by id product success")
                .build();
    }

    @PostMapping("")
    public ResponseDTO<ProductDTO> create(@RequestBody @Valid ProductDTO productDTO) {
        productService.create(productDTO);
        return ResponseDTO.<ProductDTO>builder()
                .data(productDTO)
                .status(200)
                .msg("Get by id product success")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<ProductDTO> update(@RequestBody @Valid ProductDTO productDTO,
                                          @PathVariable("id") Integer id) {
        productDTO.setId(id);
        productService.create(productDTO);
        return ResponseDTO.<ProductDTO>builder()
                .data(productDTO)
                .status(200)
                .msg("Get by id product success")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("delete product success")
                .build();
    }
}
