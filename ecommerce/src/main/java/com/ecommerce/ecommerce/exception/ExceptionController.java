package com.ecommerce.ecommerce.exception;

import com.ecommerce.ecommerce.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

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

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseDTO<String> accessDenied(AccessDeniedException e){
        return ResponseDTO.<String>builder()
                .status(403)
                .msg("Access denied")
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseDTO<String> badCredential(BadCredentialsException e){
        return ResponseDTO.<String>builder()
                .msg("Tên đăng nhập hoặc mật khẩu không đúng")
                .status(403)
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO<String> exception(Exception e){
        return ResponseDTO.<String>builder()
                .status(500)
                .msg("Lỗi")
                .build();
    }
}
