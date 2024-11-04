package com.fakeBankDetails.fakeBank.service.interfaces;

public interface EmailServiceInterface {
    void sendOTPinMail(String to , String subject , String body);
}
