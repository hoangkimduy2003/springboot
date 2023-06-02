package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @PostMapping("")
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductDTO productDTO,
                             @PathVariable("id") Long id) {
        productDTO.setId(id);
        return productService.update(productDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

}
