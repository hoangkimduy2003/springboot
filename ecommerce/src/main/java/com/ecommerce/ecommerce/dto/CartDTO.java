package com.ecommerce.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long id;
    @JsonIgnore
    private UserDTO user;
    private List<CartDetailDTO> cartDetails;

}
