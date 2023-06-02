package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.*;
import lombok.Data;

@Data
public class ProductDetailDTO {
    private Long id;
    private String image;
    private Long quantity;
    private CategoryDTO category;
    private ColorDTO color;
    private SizeDTO size;
}
