package com.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductDetail productDetail;
    private Long quantity;
    private BigDecimal totalMoney;

    @ManyToOne
    @JsonIgnoreProperties("cartDetails")
    private Cart cart;

    private BigDecimal price;

    private boolean status;

}
