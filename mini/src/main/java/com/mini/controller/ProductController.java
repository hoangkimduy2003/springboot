package com.mini.controller;

import com.mini.dto.PageDTO;
import com.mini.dto.ProductDTO;
import com.mini.dto.ResponseDTO;
import com.mini.dto.SearchDTO;
import com.mini.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/search")
    public ResponseDTO<List<ProductDTO>> search(@RequestBody SearchDTO searchDTO) {
        return ResponseDTO.<List<ProductDTO>>builder()
                .data(productService.search(searchDTO).getData())
                .status(200)
                .msg("Get success")
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDTO<ProductDTO> getById(@PathVariable("id") Long id) {
        return ResponseDTO.<ProductDTO>builder()
                .data(productService.getById(id))
                .status(200)
                .msg("Get success")
                .build();
    }

    @PostMapping("")
    public ResponseDTO<ProductDTO> create(@ModelAttribute @Valid ProductDTO productDTO) throws Exception {
        List<String> images = new ArrayList<>();
        if(productDTO.getFiles() != null){
            for (MultipartFile multipartFile : productDTO.getFiles()) {
                String nameFile = multipartFile.getOriginalFilename();
                images.add(nameFile);
                File file = new File("D:/project_vuejs/project/projectVueJs/mini/src/assets/images/" + nameFile);
                multipartFile.transferTo(file);
            }
        }

        productDTO.setImages(images);
        productService.create(productDTO);
        return ResponseDTO.<ProductDTO>builder()
                .data(productDTO)
                .status(200)
                .msg("Create success")
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDTO<ProductDTO> update(@ModelAttribute @Valid ProductDTO productDTO,
                                          @PathVariable("id") Long id) throws Exception {
        List<String> images = new ArrayList<>();
        if(productDTO.getFiles() != null){
            for (MultipartFile multipartFile : productDTO.getFiles()) {
                String nameFile = multipartFile.getOriginalFilename();
                images.add(nameFile);
                File file = new File("D:/project_vuejs/project/projectVueJs/mini/src/assets/images/" + nameFile);
                multipartFile.transferTo(file);
            }
            productDTO.setImages(images);
        }
        else{
            ProductDTO product = productService.getById(id);
            productDTO.setImages(product.getImages());
        }

        productDTO.setId(id);
        productService.update(productDTO);
        return ResponseDTO.<ProductDTO>builder()
                .data(productDTO)
                .status(200)
                .msg("Update success")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseDTO.<Void>builder()
                .status(200)
                .msg("Delete success")
                .build();
    }

}
