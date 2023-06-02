package com.education.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDTO  extends TimeAuditable{
    private  Integer id;
    private String name;
}
