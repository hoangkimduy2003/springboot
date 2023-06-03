package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.reponsitory.ProductRepo;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

//    @GetMapping("/image/{fileName}")
//    public void showImage(@PathVariable("fileName") String fileName){
//        File file = new File("D:/file/"+fileName);
//        Files.copy(file.toPath());
//    }

    @PostMapping("")
    public ProductDTO add(@ModelAttribute ProductDTO productDTO) throws IOException {
//        List<String> images = new ArrayList<>();
//        if (productDTO.getFiles() != null) {
//            for (MultipartFile x : productDTO.getFiles() ) {
//                String name = x.getOriginalFilename();
//
//                File file = new File("D:/file/" + name);
//                x.transferTo(file);
//
//                images.add(name);
//            }
//        }
//        productDTO.setImages(images);
        productService.create(productDTO);
        return productDTO;
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductDTO productDTO,
                             @PathVariable("id") Integer id) {
        productDTO.setId(id);
        productService.update(productDTO);
        return productDTO;
    }

    @DeleteMapping("/{id}")
    public void update( @PathVariable("id") Integer id) {
        productService.delete(id);

    }
}
