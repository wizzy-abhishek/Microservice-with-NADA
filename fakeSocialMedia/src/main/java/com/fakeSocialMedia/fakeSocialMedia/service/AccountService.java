package com.fakeSocialMedia.fakeSocialMedia.service;

import com.fakeSocialMedia.fakeSocialMedia.dto.AccountDTO;

import java.util.List;
import java.util.Set;

public interface AccountService {

    AccountDTO createNewAccount(AccountDTO accountDTO);
    AccountDTO findAccountById(String accountId );
    List<AccountDTO> dumpAllAccount(List<AccountDTO> accountDTOS);
}
