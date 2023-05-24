package com.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Integer id;
    private BigDecimal price;
    private String name;
    private String description;
}
