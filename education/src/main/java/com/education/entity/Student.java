package com.education.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String studentCode;
    private String fullName;
    private Date birthDate;
    private Boolean sex;

    @OneToMany(mappedBy = "student")
    private List<Score> scores;

    @ManyToOne
    private Department department;
}
