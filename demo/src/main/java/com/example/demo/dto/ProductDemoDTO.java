package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ElementCollection;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductDemoDTO {
    private Long id;
    private String name;
    private List<byte[]> listImages;

    @JsonIgnore
    private List<MultipartFile> files;

}
