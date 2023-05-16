package com.project2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReponseDTO<T> {
    private int status;
    private  String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ReponseDTO(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
