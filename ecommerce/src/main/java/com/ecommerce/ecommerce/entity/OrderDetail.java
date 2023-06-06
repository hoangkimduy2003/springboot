package com.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductDetail productDetail;

    private Long quantity;
    private BigDecimal totalMoney;

    @ManyToOne
    @JsonIgnore
    private Orders orders;

    private BigDecimal price;

    @PrePersist
    @PreUpdate
    public void pre(){
        setPrice(productDetail.getPrice());
        BigDecimal total = price.multiply(new BigDecimal(quantity));
        setTotalMoney(total);
    }
}
