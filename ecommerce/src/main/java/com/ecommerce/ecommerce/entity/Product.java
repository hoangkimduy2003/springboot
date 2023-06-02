package com.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Product extends TimeAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long totalQuantity;
    private Long totalQuantitySold;
    private BigDecimal importPrice;
    private String description;

    @ElementCollection
    private List<String> images;

    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetails;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Category category;

}
