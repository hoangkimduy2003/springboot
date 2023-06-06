package com.ecommerce.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonIgnore
    private Cart cart;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Orders> orders;

    @ElementCollection
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<String> roles;

    @PrePersist
    public void pre(){
        Cart cartUser = new Cart();
        cartUser.setUser(this);
        setCart(cartUser);
    }
}
