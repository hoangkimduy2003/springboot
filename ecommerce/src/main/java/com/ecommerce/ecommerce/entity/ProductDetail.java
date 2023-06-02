package com.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private Long quantity;

    @ManyToOne
    private Color color;

    @ManyToOne
    private Size size;

    @ManyToOne
    @JsonIgnore
    private Product product;


}
