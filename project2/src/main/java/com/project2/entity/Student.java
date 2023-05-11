package com.project2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Student {
    @Id
    private Integer id;

    @MapsId
    @OneToOne
    private User user;

    private String studentCode;

    @OneToMany(mappedBy = "student")
    private List<Score> scores;
}
