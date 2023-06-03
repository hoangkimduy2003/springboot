package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductDTO {
    private Integer id;

    private String Name;
    private Long totalQuantity;

    private List<String> images;

    private List<ProductDemoDTO> productDemos;

    @JsonIgnore
    private List<MultipartFile> files;
}
