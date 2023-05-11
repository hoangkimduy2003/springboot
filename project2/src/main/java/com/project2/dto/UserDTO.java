package com.project2.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class UserDTO {
    private List<String> roles;
    private Integer id;
    private Integer age;
    private String name;
    private String avatarURL;

    private String username;
    private String password;

    private String homeAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
}
