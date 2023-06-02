package com.education.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO extends TimeAuditable{
    private Integer id;
    private String name;
    @JsonIgnore
    private List<StudentDTO> students;

}
