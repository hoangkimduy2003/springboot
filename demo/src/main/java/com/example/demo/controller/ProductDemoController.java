package com.example.demo.controller;

import com.example.demo.dto.ProductDemoDTO;
import com.example.demo.service.ProductDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pro")
@CrossOrigin
public class ProductDemoController {
    @Autowired
    private ProductDemoService productDemoService;

    @GetMapping("")
    public List<ProductDemoDTO> getAll() {
        return productDemoService.getAll();
    }


    @PostMapping("")
    public ProductDemoDTO addObject(@ModelAttribute ProductDemoDTO productDemoDTO) {
        List<byte[]> list = new ArrayList<>();
        try {

            if(productDemoDTO.getFiles()!= null){
                for (MultipartFile image : productDemoDTO.getFiles()) {
                    list.add(image.getBytes());
                }
            }
        } catch (Exception e) {

        }
        productDemoDTO.setListImages(list);
        productDemoService.create(productDemoDTO);
        return productDemoDTO;
    }
}
