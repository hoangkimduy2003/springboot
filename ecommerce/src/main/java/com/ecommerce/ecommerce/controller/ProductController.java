package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.service.IProductService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("")
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
//    @Secured({"ROLE_ADMIN"})
//    @RolesAllowed({"ROLE_ADMIN"})
//    @PreAuthorize("hasAnyAtu")
    public ProductDTO getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @GetMapping("/banchay/{index}/{size}")
    public List<ProductDTO> getBanChay(@PathVariable("index") int index,
                                       @PathVariable("size") int size) {
        List<ProductDTO> list = productService.getBanChay(index,size).getData();
        return list;
    }

    @GetMapping("/new/{index}/{size}")
    public List<ProductDTO> getNew(@PathVariable("index") int index,
                                   @PathVariable("size") int size) {
        List<ProductDTO> list = productService.getNew(index,size).getData();
        return list;
    }


    @PostMapping("")
    public ProductDTO create(@RequestBody ProductDTO productDTO) throws IOException {
        List<String> imgs = new ArrayList<>();
        if (productDTO.getFiles() != null) {
            for (MultipartFile x : productDTO.getFiles()) {
                imgs.add(x.getOriginalFilename());
                File fileSave =
                        new File("C:/Duyhkph28819/Web207/Assignment/images/" + x.getOriginalFilename());
                x.transferTo(fileSave);
            }
        }
        return productService.create(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductDTO productDTO,
                             @PathVariable("id") Long id) throws Exception {
        List<String> imgs = new ArrayList<>();
        if(productDTO.getFiles() != null){
            for (MultipartFile x : productDTO.getFiles()) {
                imgs.add(x.getOriginalFilename());
                File fileSave =
                        new File("C:/Duyhkph28819/Web207/Assignment/images/" + x.getOriginalFilename());
                x.transferTo(fileSave);
            }
        }
        productDTO.setId(id);
        return productService.update(productDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }

}
