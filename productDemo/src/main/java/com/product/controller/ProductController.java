package com.product.controller;

import com.product.dto.ProductDTO;
import com.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping("")
    public List<ProductDTO> getAll(){
        return productService.getAll();
    }

    @PostMapping("")
    public void create(@RequestBody ProductDTO productDTO){
        productService.create(productDTO);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody ProductDTO productDTO,
                       @PathVariable("id") Integer id){
        productDTO.setId(id);
        productService.update(productDTO);
    }

    @DeleteMapping("/{id}")
    public void update(@PathVariable("id") Integer id){
        productService.delete(id);
    }
}
