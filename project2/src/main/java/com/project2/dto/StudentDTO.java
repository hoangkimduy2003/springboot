package com.project2.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Integer id;
    private UserDTO user;
    private String studentCode;
}
