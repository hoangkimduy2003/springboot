package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.ProductDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Long totalQuantity;
    private Long totalQuantitySold;
    private BigDecimal importPrice;
    private BigDecimal price;
    private String description;
    private List<String> images;
    private List<ProductDetailDTO> productDetails;
    private CategoryDTO category;
    @JsonIgnore
    private List<MultipartFile> files;
}
