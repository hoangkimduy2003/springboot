package com.project1.dto;

import com.project1.entity.Department;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
public class TicketDTO {
    private Integer id;
    private String name;
    private String description;
    private DepartmentDTO department;
    private String phoneNumber;
    private String customerName;
    private String customerReviews;
    private boolean status;
    private Date createdAt;
    private Date updatedAt;
}
