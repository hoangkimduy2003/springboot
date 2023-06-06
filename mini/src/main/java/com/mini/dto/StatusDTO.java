package com.mini.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StatusDTO {
    private Long id;
    @NotBlank
    private String name;
}
