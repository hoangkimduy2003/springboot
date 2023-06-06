package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.Orders;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
    private List<String> roles;
    private CartDTO cart;
}
