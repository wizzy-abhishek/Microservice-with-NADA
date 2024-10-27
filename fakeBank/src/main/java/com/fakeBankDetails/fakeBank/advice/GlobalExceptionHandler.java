package com.fakeBankDetails.fakeBank.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleUltimateError(Exception exception){

        APIError apiError = new APIError();

        apiError.setStatusCode(HttpStatus.BAD_GATEWAY);
        apiError.setError(exception.getMessage());
        apiError.getSubErrors().add(null);

        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(apiError);
    }

}
