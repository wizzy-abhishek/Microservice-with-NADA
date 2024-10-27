package com.fakeBankDetails.fakeBank.dto;

import com.fakeBankDetails.fakeBank.enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {

    @Email
    @NotNull
    private String email ;

    @NotNull
    private long mobile ;

    @NotNull
    private Set<Roles> roles ;

}
