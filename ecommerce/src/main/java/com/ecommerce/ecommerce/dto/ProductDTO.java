package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.ProductDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    @NotBlank(message = "Name product cannot be blank")
    private String name;
    private Long totalQuantity;
    private Long totalQuantitySold;
    @NotNull(message = "Import price product cannot be blank")
    @Min(value = 1,message = "Entry price must be greater than 0")
    private BigDecimal importPrice;

    @NotNull(message = "Sell price product cannot be blank")
    @Min(value = 1,message = "Selling price must be greater than 0")
    private BigDecimal price;
    private String description;
    private List<String> images;
    private List<ProductDetailDTO> productDetails;

    @NotBlank(message = "Category cannot be blank")
    private CategoryDTO category;
    @JsonIgnore
    private List<MultipartFile> files;
}
