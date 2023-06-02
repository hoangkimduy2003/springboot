package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Orders;
import com.ecommerce.ecommerce.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDTO {
    private Long id;
    private ProductDTO product;
    private Long quantity;
    private BigDecimal totalMoney;
    private OrdersDTO orders;
}
