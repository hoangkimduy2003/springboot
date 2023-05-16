package com.managefriends.managefriends.dto;

import lombok.Data;

@Data
public class FriendDTO {
    private Integer id;

    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private String country;
}
