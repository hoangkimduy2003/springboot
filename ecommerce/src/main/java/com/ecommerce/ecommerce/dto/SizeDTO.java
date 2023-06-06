package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SizeDTO {
    private Long id;
    @NotBlank(message = "Color name cannot be blank")
    private String name;
}
