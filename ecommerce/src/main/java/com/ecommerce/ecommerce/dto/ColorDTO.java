package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ColorDTO {
    private Long id;

    @NotBlank(message = "Name color cannot be blank")
    private String name;}
