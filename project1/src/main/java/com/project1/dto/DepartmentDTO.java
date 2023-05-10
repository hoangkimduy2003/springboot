package com.project1.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DepartmentDTO {
    private Integer id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
