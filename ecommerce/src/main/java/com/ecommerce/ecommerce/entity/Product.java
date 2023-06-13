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
    private BigDecimal price;
    private Long totalQuantity;
    private Long totalQuantitySold;
    private BigDecimal importPrice;
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductDetail> productDetails;


    @ManyToOne
    private Category category;

    //after update and create in db
    @PrePersist
    @PreUpdate
    public void preCreate() {
        long totalSold = 0;
        long totalQuanti = 0;
        for (ProductDetail detail : productDetails) {
            totalQuanti += detail.getQuantity();
            totalSold += detail.getQuantitySold();
            detail.setProduct(this);
        }
        setTotalQuantitySold(totalSold);
        setTotalQuantity(totalQuanti);
    }
}
