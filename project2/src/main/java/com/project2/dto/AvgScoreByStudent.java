package com.project2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvgScoreByStudent {
    private Integer studentId;
    private String studentCode;
    private String nameStudent;
    private Double avg;
}
