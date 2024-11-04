package com.fakeBankDetails.fakeBank.exceptions;

public class InsufficientFundException extends RuntimeException {
    public InsufficientFundException(String message) {
        super(message);
    }
}
