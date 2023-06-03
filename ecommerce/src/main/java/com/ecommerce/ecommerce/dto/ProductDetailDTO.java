package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDetailDTO {
    private Long id;
    private Long quantity;
    private Long quantitySold;
    private ColorDTO color;
    private SizeDTO size;
}
