package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Orders;
import com.ecommerce.ecommerce.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDTO {
    private Long id;
    private ProductDetailDTO productDetail;
    @NotNull(message = "Quantity cannot be blank")
    @Min(value = 1 ,message = "Number must be greater than 0")
    private Long quantity;
    private BigDecimal totalMoney;
    private BigDecimal price;

    @JsonIgnore
    private OrdersDTO orders;
}
