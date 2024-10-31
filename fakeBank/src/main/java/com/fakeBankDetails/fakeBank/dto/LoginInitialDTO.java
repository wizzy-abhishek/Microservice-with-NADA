package com.fakeBankDetails.fakeBank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginInitialDTO {

    @Email
    @NotNull
    private final String email ;
}
