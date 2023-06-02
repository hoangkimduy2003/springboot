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


    @ElementCollection
    private List<String> images;

//    @ManyToMany
//    @JoinTable(name = "product_brand",
//    joinColumns = @JoinColumn(name = "product_id"),
//    inverseJoinColumns = @JoinColumn(name = "brand_id"))
//    private List<Brand> brands;

}
