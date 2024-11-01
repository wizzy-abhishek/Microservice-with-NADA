package com.fakeBankDetails.fakeBank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginFinalDTO {

    private String email ;

    private String otp ;

}
