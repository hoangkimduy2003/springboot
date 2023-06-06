package com.ecommerce.ecommerce.exception;

import com.ecommerce.ecommerce.dto.ResponseDTO;
import jakarta.persistence.NoResultException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDTO<String> notFound(IllegalArgumentException e){
        return ResponseDTO.<String>builder()
                .status(404)
                .msg("No Data")
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<String> validationFail(MethodArgumentNotValidException e){
        return ResponseDTO.<String>builder()
                .status(401)
                .msg(e.getBindingResult().getFieldError().getDefaultMessage())
                .build();
    }
}
