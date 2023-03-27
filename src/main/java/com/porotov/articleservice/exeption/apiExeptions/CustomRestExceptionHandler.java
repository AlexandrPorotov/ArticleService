package com.porotov.articleservice.exeption.apiExeptions;

import com.porotov.articleservice.exeption.apiExeptions.exeptions.ResourceAlreadyExistsException;
import com.porotov.articleservice.exeption.apiExeptions.exeptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        String error = "Resource not found exception";
        ApiError apiError =
                new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> resourceAlreadyExistException( ResourceAlreadyExistsException ex, WebRequest request){
        String error = "Resource already exist exception";

        ApiError apiError =
                new ApiError(HttpStatus.CONFLICT, ex.getMessage(), error);
        return new ResponseEntity<>(
                apiError,new HttpHeaders(),apiError.getStatus());
    }



}
