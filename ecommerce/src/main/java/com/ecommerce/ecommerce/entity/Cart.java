package com.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToOne
    @JsonIgnore
    private User user;

    private BigDecimal totalMoney;
    private Long totalProduct;

    @OneToMany(mappedBy = "cart",
            cascade = CascadeType.ALL)
    private List<CartDetail> cartDetails;

    @PrePersist
    public void pre(){
        setTotalMoney(new BigDecimal(0));
        setTotalProduct(0L);
    }

}
