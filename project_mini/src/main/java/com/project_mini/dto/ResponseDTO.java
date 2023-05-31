package com.project_mini.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private String msg;
    private Integer status;

    private T data;

    public ResponseDTO(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }
}
