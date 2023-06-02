package com.education.dto;

import com.education.entity.Course;
import com.education.entity.Student;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ScoreDTO {
    private int id;

    private Double score;

    private CourseDTO course;

    private StudentDTO student;
    }
