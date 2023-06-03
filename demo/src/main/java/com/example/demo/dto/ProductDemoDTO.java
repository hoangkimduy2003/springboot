package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ProductDemoDTO {
    private Long id;
    private String quantity;
    @JsonIgnore
    private ProductDTO productDTO;


}
