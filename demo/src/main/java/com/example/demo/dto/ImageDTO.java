package com.example.demo.dto;

import com.example.demo.entity.Product;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ImageDTO {
    private Long id;
    private String fileName;
    private String contentType;
    private Byte[] data;
}
