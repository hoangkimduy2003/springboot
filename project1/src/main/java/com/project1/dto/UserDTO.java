package com.project1.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private DepartmentDTO department;
    private String name;
    private String username;
    private String password;
}
