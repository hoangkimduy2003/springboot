package com.project1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket extends AtDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String phoneNumber;
    private String customerName;
    private String customerReviews;
    private boolean status;
    @ManyToOne
    private Department department;
}
