package com.example.demotieptheo.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private List<String> roles;
}
