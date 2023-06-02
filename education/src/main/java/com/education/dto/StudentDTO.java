package com.education.dto;

import com.education.entity.Department;
import com.education.entity.Score;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StudentDTO {
    private Integer id;

    private String studentCode;
    private String fullName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private Boolean sex;
    private DepartmentDTO department;
}
