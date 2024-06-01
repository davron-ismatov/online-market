package com.example.auth.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice

public class AppExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<Map<String,String>> errorList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().stream().forEach(error ->{
                Map<String ,String > errors = new HashMap<>();
                errors.put("field", ((FieldError) error).getField());
                errors.put("description",error.getDefaultMessage());
                errorList.add(errors);
        });
        return errorList;
    }

    @ExceptionHandler(UserAttachmentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Map<String,String>> handleUserAttachmentException(UserAttachmentException ex){
        return List.of(Map.of("message",ex.getMessage()));
    }

    @ExceptionHandler(AttachmentNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Map<String,String>> handleAttachmentNotFoundException(AttachmentNotFoundException ex){
        return List.of(Map.of("message",ex.getMessage()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Map<String,String>> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex){

        return List.of(Map.of("message",ex.getMessage()));
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public List<Map<String,String>> handleMethodArgumentNotValidException(TokenExpiredException ex){
        return List.of(Map.of("message",ex.getMessage()));
    }
}
