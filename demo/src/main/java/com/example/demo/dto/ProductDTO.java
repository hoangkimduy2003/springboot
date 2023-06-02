package com.example.demo.dto;

import com.example.demo.entity.Brand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductDTO {
    private Integer id;

    private String Name;

    private List<String> images;

    @JsonIgnore
    private List<MultipartFile> files;
}
