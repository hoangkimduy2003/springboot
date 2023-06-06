package com.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Cart cart;

    private BigDecimal price;

    private boolean status;

    @PrePersist
    @PreUpdate
    public void pre(){
        setPrice(productDetail.getPrice());
        BigDecimal total = price.multiply(new BigDecimal(quantity));
        setTotalMoney(total);
    }

}
