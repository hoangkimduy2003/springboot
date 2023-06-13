package com.ecommerce.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private BigDecimal totalMoney;
    private Long totalProduct;
    @JsonIgnoreProperties("cart")
    private UserDTO user;
    private List<CartDetailDTO> cartDetails;

}
