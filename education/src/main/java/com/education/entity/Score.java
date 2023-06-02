package com.education.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Score extends TimeAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double score;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;
}
