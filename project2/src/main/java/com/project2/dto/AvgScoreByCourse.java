package com.project2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvgScoreByCourse {
    private Integer courseId;
    private String courseName;
    private Double avg;
}
