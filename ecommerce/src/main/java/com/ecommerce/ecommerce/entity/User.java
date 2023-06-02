package com.ecommerce.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;

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

}
