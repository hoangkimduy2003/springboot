package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    @NotBlank(message = "Name category cannot be blank")
    private String name;
}
