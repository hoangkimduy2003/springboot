package com.example.demo.service;

import com.example.demo.dto.ProductDemoDTO;
import com.example.demo.entity.ProductDemo;
import com.example.demo.reponsitory.ProductDemoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDemoService {
    @Autowired
    private ProductDemoRepo productDemoRepo;

    public ProductDemoDTO convertToDto(ProductDemo productDemo) {
        return new ModelMapper().map(productDemo, ProductDemoDTO.class);
    }

    public ProductDemo convertToEntity(ProductDemoDTO productDemoDTO) {
        return new ModelMapper().map(productDemoDTO, ProductDemo.class);
    }

    public List<ProductDemoDTO> getAll() {
        return productDemoRepo.findAll().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }

    public void create(ProductDemoDTO productDemoDTO) {
        productDemoRepo.save(convertToEntity(productDemoDTO));
    }

    public void update(ProductDemoDTO productDemoDTO) {
        ProductDemo productDemo = productDemoRepo.findById(productDemoDTO.getId()).orElse(null);
        if (productDemo != null) {
            productDemo = convertToEntity(productDemoDTO);
        }
        productDemoRepo.save(productDemo);
    }

    public void delete(Long id) {
        productDemoRepo.deleteById(id);
    }

    public ProductDemoDTO getById(Long id){
        return convertToDto(productDemoRepo.findById(id).orElse(null));
    }


}
