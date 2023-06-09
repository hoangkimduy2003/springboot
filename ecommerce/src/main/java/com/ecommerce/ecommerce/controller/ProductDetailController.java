package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.dto.ProductDetailDTO;
import com.ecommerce.ecommerce.service.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productDetail")
@CrossOrigin
public class ProductDetailController {
    @Autowired
    private IProductDetailService productDetailService;

    @GetMapping("")
    public List<ProductDetailDTO> getAll() {
        return productDetailService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDetailDTO getById(@PathVariable("id") Long id) {
        return productDetailService.getById(id);
    }

    @PostMapping("")
    public ProductDetailDTO create(@RequestBody ProductDetailDTO productDetailDTO) {
        return productDetailService.create(productDetailDTO);
    }

    @PutMapping("/{id}")
    public ProductDetailDTO update(@RequestBody ProductDetailDTO productDetailDTO,
                             @PathVariable("id") Long id) {
        productDetailDTO.setId(id);
        return productDetailService.update(productDetailDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productDetailService.delete(id);
    }

}
