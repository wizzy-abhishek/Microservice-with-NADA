package com.fakeBankDetails.fakeBank.service.interfaces;

import com.fakeBankDetails.fakeBank.dto.FinalLoginResponseDTO;
import com.fakeBankDetails.fakeBank.dto.LoginInitialResponseDTO;
import com.fakeBankDetails.fakeBank.dto.UserLoginFinalDTO;

public interface AuthLoginServiceInterface {
    FinalLoginResponseDTO loginFinal(UserLoginFinalDTO loginDTO);
    LoginInitialResponseDTO loginInitial(String email) ;
}
