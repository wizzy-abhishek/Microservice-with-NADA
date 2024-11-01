package com.fakeBankDetails.fakeBank.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class APIError {

    private LocalDateTime timeStamp ;
    private HttpStatus statusCode ;
    private String error ;
    private List<String> subErrors ;

    public APIError() {
        this.timeStamp = LocalDateTime.now();
        this.subErrors = new ArrayList<>(5);
    }

    public APIError(String error, HttpStatus statusCode) {
        this();
        this.error = error;
        this.statusCode = statusCode;
    }

}
