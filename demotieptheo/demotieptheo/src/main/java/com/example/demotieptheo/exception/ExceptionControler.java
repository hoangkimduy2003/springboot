package com.example.demotieptheo.exception;

import com.example.demotieptheo.dto.ResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ExceptionControler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDTO<String> findNoResult(IllegalArgumentException e) {
        return ResponseDTO.<String>builder()
                .data("no data")
                .status(404)
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseDTO<String> accessDined(AccessDeniedException e) {
        return ResponseDTO.<String>builder()
                .data("Access denied")
                .status(403)
                .build();
    }
}
