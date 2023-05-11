package com.project2.dto;

import lombok.Data;

@Data
public class PageDTO<T> {
    private int totalPages;
    private long totalElements;

    private T data;
}
