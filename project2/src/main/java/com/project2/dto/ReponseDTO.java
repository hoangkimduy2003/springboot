package com.project2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReponseDTO<T> {
    private int status; // 200,400,500
    private  String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ReponseDTO(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
