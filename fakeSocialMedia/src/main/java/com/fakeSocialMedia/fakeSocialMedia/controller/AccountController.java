package com.fakeSocialMedia.fakeSocialMedia.controller;

import com.fakeSocialMedia.fakeSocialMedia.dto.AccountDTO;
import com.fakeSocialMedia.fakeSocialMedia.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/")
public class AccountController {

    private final AccountService accountService ;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("account")
    public ResponseEntity<AccountDTO> createNewAccount(@RequestBody @Valid AccountDTO accountDTO){
        AccountDTO processAccountDTO = accountService.createNewAccount(accountDTO);
        return ResponseEntity.ok(processAccountDTO);
    }
}
