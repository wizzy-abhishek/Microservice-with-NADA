package com.fakeSocialMedia.fakeSocialMedia.advice;

import com.fakeSocialMedia.fakeSocialMedia.exceptions.DuplicateResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<APIError> handleDuplicateResource(DuplicateResourceException exception){

        APIError apiError = new APIError();

        apiError.setStatusCode(HttpStatus.BAD_REQUEST);
        apiError.setError(exception.getMessage());
        apiError.getSubErrors().add(null);

        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST) ;
    }
}
