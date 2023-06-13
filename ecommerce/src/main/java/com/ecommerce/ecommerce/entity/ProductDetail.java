package com.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long quantity;
    private Long quantitySold;
    private BigDecimal price;

    @ManyToOne
    private Color color;

    @ManyToOne
    private Size size;

    @ManyToOne
    @JsonIgnore
    private Product product;

    @OneToMany(mappedBy = "productDetail")
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "productDetail")
    @JsonIgnore
    private List<CartDetail> cartDetails;

    @PrePersist
    public void Pre(){
        long quantity = 0;
        setPrice(product.getPrice());
        setName(product.getName());
        setQuantitySold(quantity);
    }
    @PreUpdate
    public void update(){
        setPrice(product.getPrice());
        setName(product.getName());
    }


}
