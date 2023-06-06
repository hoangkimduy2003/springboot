package com.mini.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private Long quantity;

    @NotNull
    @Min(1)
    private Double originPrice;

    @NotNull
    @Min(1)
    private Double sellPrice;

    private CategoryDTO category;
    private BrandDTO brand;
    private StatusDTO status;
    private List<String> images;

    @JsonIgnore
    private List<MultipartFile> files;
}
