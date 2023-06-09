package com.ecommerce.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductDetailDTO {
    private Long id;

    private String name;
    private Long quantity;
    private Long quantitySold;
    private BigDecimal price;
    private ColorDTO color;
    private SizeDTO size;

    @JsonIgnoreProperties("productDetails")
    private ProductDTO product;
}
