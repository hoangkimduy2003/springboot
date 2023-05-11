package com.project2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Score extends TimeAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double score;
    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;
}
