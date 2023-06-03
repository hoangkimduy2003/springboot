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
    private BigDecimal price;
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<ProductDetail> productDetails;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartDetail> cartDetails;

    @ManyToOne
    private Category category;

    //after update and create in db
    @PrePersist
    @PreUpdate
    public void updateQuantity() {
        long totalQuanti = 0;
        long totalQuantiSole = 0;
        for (ProductDetail detail : productDetails) {
            totalQuantiSole += (detail.getQuantitySold() + detail.getQuantitySold());
            totalQuanti += detail.getQuantity();
            detail.setProduct(this);
        }
        setTotalQuantity(totalQuanti);
        setTotalQuantitySold(totalQuantiSole);
    }

}
