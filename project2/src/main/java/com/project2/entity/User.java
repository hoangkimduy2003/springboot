package com.project2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ElementCollection
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles;

    private int age;
    private String name;
    private String avatarURL;

    private String username;
    private String password;

    private String homeAddress;

    @Temporal(TemporalType.DATE)
    private Date birthdate;


}
