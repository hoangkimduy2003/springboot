package com.ecommerce.ecommerce.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.MapsId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDetailDTO {
    private Long id;
    private ProductDetailDTO productDetail;
    private Long quantity;
    private BigDecimal price;
    private BigDecimal totalMoney;
    private boolean status;
    @JsonIgnoreProperties("cartDetails")
//    @JsonIgnore
    private CartDTO cart;

}
