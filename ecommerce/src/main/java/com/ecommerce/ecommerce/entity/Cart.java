package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(mappedBy = "cart")
    private List<Product> products;

    @OneToOne
    private User user;

    private Long quantity;

    private BigDecimal totalMoney;
}
