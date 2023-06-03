package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Name;

    private Long totalQuantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    private List<ProductDemo> productDemos;

//    @PrePersist
//    @PreUpdate
//    public void create() {
//        long total = 0;
//        for (ProductDemo p : productDemos) {
//            p.setProduct(this);
//            total += p.getQuantity();
//        }
//        setTotalQuantity(total);
//    }

    @ElementCollection
    private List<String> images;
}
