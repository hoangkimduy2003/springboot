package com.mini.dto;

import lombok.Data;

@Data
public class SearchDTO {
    private String name;
    private Long brandId;
    private Long statusId;
    private Long categoryId;
    private Integer pageIndex;
    private Integer pageSize;
}
