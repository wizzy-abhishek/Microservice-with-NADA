package com.fakeSocialMedia.fakeSocialMedia.service;

import com.fakeSocialMedia.fakeSocialMedia.dto.AccountDTO;
import com.fakeSocialMedia.fakeSocialMedia.entity.Account;
import com.fakeSocialMedia.fakeSocialMedia.exceptions.DuplicateResourceException;
import com.fakeSocialMedia.fakeSocialMedia.repository.AccountRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepo accountRepo ;
    private final ModelMapper modelMapper ;

    public AccountService(AccountRepo accountRepo, ModelMapper modelMapper) {
        this.accountRepo = accountRepo;
        this.modelMapper = modelMapper;
    }

    private boolean doesAccountExistsById(String id){
        return accountRepo.existsById(id) ;
    }

    private boolean doesAccountExistsByEmail(String email){
        return accountRepo.existsByEmail(email);
    }

    private boolean doesAccountExistsByMobile(long mobile){
        return accountRepo.existsByMobile(mobile);
    }

    @Transactional
    public AccountDTO createNewAccount(AccountDTO accountDTO){

        if (doesAccountExistsById(accountDTO.getAccountName())){
            throw  new DuplicateResourceException("Account with Id " + accountDTO.getAccountName() + "already exists");
        }

        if (doesAccountExistsByEmail(accountDTO.getEmail())){
            throw new DuplicateResourceException("Email " + accountDTO.getEmail() + " already exists");
        }

        if (doesAccountExistsByMobile(accountDTO.getMobile())){
            throw new DuplicateResourceException("Mobile number " + accountDTO.getMobile() + " already exists");
        }

        Account account = modelMapper.map(accountDTO , Account.class);
        Account accountSaved = accountRepo.insert(account);

        return modelMapper.map(accountSaved , AccountDTO.class);
    }
}
