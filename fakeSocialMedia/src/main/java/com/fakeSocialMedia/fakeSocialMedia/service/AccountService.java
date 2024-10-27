package com.fakeSocialMedia.fakeSocialMedia.service;

import com.fakeSocialMedia.fakeSocialMedia.dto.AccountDTO;

public interface AccountService {

    AccountDTO createNewAccount(AccountDTO accountDTO);
    AccountDTO findAccountById(String accountId);

}
