package com.fakeBankDetails.fakeBank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {

    @Email
    @NotNull
    private final String email ;
    @NotNull
    private final int otp ;
}
