package com.fakeBankDetails.fakeBank.controller;

import com.fakeBankDetails.fakeBank.dto.*;
import com.fakeBankDetails.fakeBank.service.AuthLoginService;
import com.fakeBankDetails.fakeBank.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService ;
    private final AuthLoginService authLoginService ;

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpDTO signUpDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(signUpDTO));
    }

    @PostMapping("/login/{email}")
    public ResponseEntity<LoginInitialResponseDTO> login(@PathVariable String email){
        LoginInitialResponseDTO loginInitialResponseDTO = authLoginService.loginInitial(email);
        return ResponseEntity.ok(loginInitialResponseDTO);
    }

    @PostMapping("/loginFinal")
    public ResponseEntity<FinalLoginResponseDTO> loginInitial(@RequestBody UserLoginFinalDTO userLoginFinalDTO
                                                            , HttpServletResponse response) throws Exception {

        FinalLoginResponseDTO loginResponseDTO = authLoginService.loginFinal(userLoginFinalDTO);
        Cookie cookie = new Cookie("refreshToken" , loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public void refreshToken(){

    }
}
