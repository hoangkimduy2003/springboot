package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.ProductDetail;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Long totalQuantity;
    private Long totalQuantitySold;
    private BigDecimal importPrice;
    private String description;
    private List<String> images;
    private List<ProductDetailDTO> productDetails;
}
