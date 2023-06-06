package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Cart {

    @Id
    public Long id;

    @OneToOne
    @MapsId
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<CartDetail> cartDetails;

}
