package com.project2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentDTO {
    private Integer id;
    @NotBlank
    private String name;
}
