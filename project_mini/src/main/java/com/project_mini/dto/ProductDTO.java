package com.project_mini.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ProductDTO {
    private Integer id;

    @NotBlank
    @Length(max = 200, min = 1)
    private String name;

    @Min(1)
    private double price;

    @NotBlank
    @Length(max = 200, min = 1)
    private String description;
}
