package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Product;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDetailDTO {
    private Long id;
    private ProductDTO product;
    private Long quantity;
    private BigDecimal totalMoney;

}
