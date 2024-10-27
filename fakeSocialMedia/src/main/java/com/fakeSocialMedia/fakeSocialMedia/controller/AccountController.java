package com.fakeSocialMedia.fakeSocialMedia.controller;

import com.fakeSocialMedia.fakeSocialMedia.dto.AccountDTO;
import com.fakeSocialMedia.fakeSocialMedia.exceptions.UnallowedAPIException;
import com.fakeSocialMedia.fakeSocialMedia.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/account" })
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

    @GetMapping(path = {"/{accountId}" , "/nada-api-fetch/{accountId}"})
    public ResponseEntity<AccountDTO> getAccountDetails(@PathVariable String accountId , HttpServletRequest request){

        boolean isAPIRequest = request.getRequestURI().contains("/nada-api-fetch");
        AccountDTO accountDTO = accountService.findAccountById(accountId);

        if(accountDTO.isApiActive() != isAPIRequest){
            throw new UnallowedAPIException("You are not allowed to make API call on this account");
        }

        return ResponseEntity.ok(accountDTO);
    }
}
