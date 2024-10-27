package com.fakeSocialMedia.fakeSocialMedia.controller;

import com.fakeSocialMedia.fakeSocialMedia.dto.AccountDTO;
import com.fakeSocialMedia.fakeSocialMedia.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createNewAccount(@RequestBody @Valid AccountDTO accountDTO){
        AccountDTO processAccountDTO = accountService.createNewAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(processAccountDTO);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountDetails(@PathVariable String accountId){
        AccountDTO accountDTO = accountService.findAccountById(accountId);
        return ResponseEntity.ok(accountDTO);
    }
}
