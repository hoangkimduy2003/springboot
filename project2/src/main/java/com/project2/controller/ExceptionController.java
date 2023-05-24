package com.project2.controller;

import com.project2.dto.ReponseDTO;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler({NoResultException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ReponseDTO<String> notFound(NoResultException e) {
        log.info("INFO ", e);
        return ReponseDTO.<String>builder()
                .status(404)
                .msg("No data")
                .build();
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ReponseDTO<String> badRequest(BindException e) {
        log.info("bad request");
        List<ObjectError> errs = e.getBindingResult().getAllErrors();
        //List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String msg = "";
        for (ObjectError err : errs) {
            FieldError fieldError = (FieldError) err;
            msg += fieldError.getField() + ":" + err.getDefaultMessage() + ";";
        }
        return ReponseDTO.<String>builder()
                .msg(msg)
                .status(400)
                .build();
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ReponseDTO<String> duplicate(Exception e) {
        log.info("INFO", e);
        return ReponseDTO.<String>builder()
                .status(409).msg("Duplicated Data").build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ReponseDTO<String> err(Exception e){
        log.error("INFO",e);
        return ReponseDTO.<String>builder()
                .status(500)
                .msg("")
                .build();
    }


}
