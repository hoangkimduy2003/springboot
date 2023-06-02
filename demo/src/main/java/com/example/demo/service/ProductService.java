package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.reponsitory.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public ProductDTO convertToDto(Product product){
        return new ModelMapper().map(product,ProductDTO.class);
    }
    public Product convertToEntity(ProductDTO productDTO){
        return new ModelMapper().map(productDTO,Product.class);
    }
    public List<ProductDTO> getAll(){
        return productRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }
    public void create(ProductDTO productDTO){
        productRepo.save(convertToEntity(productDTO));
    }
    public void update(ProductDTO productDTO){
        Product product = productRepo.findById(productDTO.getId()).orElse(null);
        if (product != null) {
            product = convertToEntity(productDTO);
        }
        productRepo.save(product);
    }
}
