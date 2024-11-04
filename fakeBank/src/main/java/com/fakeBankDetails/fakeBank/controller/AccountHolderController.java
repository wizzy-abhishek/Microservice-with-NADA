package com.fakeBankDetails.fakeBank.controller;

import com.fakeBankDetails.fakeBank.dto.AccountHoldersDetailsDTO;
import com.fakeBankDetails.fakeBank.service.interfaces.AccountHolderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountHolder")
public class AccountHolderController {

    private final AccountHolderDetailService accountHolderDetailsService ;

    public AccountHolderController(AccountHolderDetailService accountHolderDetailsService) {
        this.accountHolderDetailsService = accountHolderDetailsService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountHoldersDetailsDTO> createNewAccount(@RequestBody AccountHoldersDetailsDTO accountHoldersDetailsDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
               .body(accountHolderDetailsService.createNewAccount(accountHoldersDetailsDTO));
    }

}
