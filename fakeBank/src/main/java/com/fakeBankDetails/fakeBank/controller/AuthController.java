package com.fakeBankDetails.fakeBank.controller;

import com.fakeBankDetails.fakeBank.dto.SignUpDTO;
import com.fakeBankDetails.fakeBank.dto.UserDTO;
import com.fakeBankDetails.fakeBank.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService ;

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpDTO signUpDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(signUpDTO));
    }
}
