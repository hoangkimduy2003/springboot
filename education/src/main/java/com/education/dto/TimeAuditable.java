package com.education.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public abstract class TimeAuditable {
    private Date createdAt;
    private Date updatedAt;
}
