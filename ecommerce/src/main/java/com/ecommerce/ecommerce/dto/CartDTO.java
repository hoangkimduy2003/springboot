package com.ecommerce.ecommerce.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO {
    public Long id;
    private List<ProductDTO> products;
    private Long quantity;
    private BigDecimal totalMoney;
}
