package com.fakeSocialMedia.fakeSocialMedia.controller;

import com.fakeSocialMedia.fakeSocialMedia.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/")
public class AccountController {

    private final AccountService accountService ;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
}
