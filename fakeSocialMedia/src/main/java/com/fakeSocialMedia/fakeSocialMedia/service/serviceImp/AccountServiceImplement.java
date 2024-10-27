package com.fakeSocialMedia.fakeSocialMedia.service.serviceImp;

import com.fakeSocialMedia.fakeSocialMedia.dto.AccountDTO;
import com.fakeSocialMedia.fakeSocialMedia.entity.Account;
import com.fakeSocialMedia.fakeSocialMedia.exceptions.DuplicateResourceException;
import com.fakeSocialMedia.fakeSocialMedia.exceptions.ResourceNotFoundException;
import com.fakeSocialMedia.fakeSocialMedia.repository.AccountRepo;
import com.fakeSocialMedia.fakeSocialMedia.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplement implements AccountService {

    private final AccountRepo accountRepo ;
    private final ModelMapper modelMapper ;

    public AccountServiceImplement(AccountRepo accountRepo, ModelMapper modelMapper) {
        this.accountRepo = accountRepo;
        this.modelMapper = modelMapper;
    }

    protected boolean doesAccountExistsById(String id){
        return accountRepo.existsById(id) ;
    }

    private boolean doesAccountExistsByEmail(String email){
        return accountRepo.existsByEmail(email);
    }

    private boolean doesAccountExistsByMobile(long mobile){
        return accountRepo.existsByMobile(mobile);
    }

    @Transactional
    @Override
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

    @Override
    @Transactional(readOnly = true)
    public AccountDTO findAccountById(String accountId) {
        Account foundAccount = accountRepo.findById(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("Account with id :" + accountId + "not found"));
        return modelMapper.map(foundAccount , AccountDTO.class);
    }

    @Override
    @Transactional
    public List<AccountDTO> dumpAllAccount(List<AccountDTO> accountDTOS) {

        List<Account> accountSet = accountDTOS.stream()
                .map(accountEntity -> modelMapper.map(accountEntity , Account.class))
                .toList();

        List<Account> createdAccounts = accountRepo.insert(accountSet);

        return createdAccounts.stream()
                .map(accountDTO -> modelMapper.map(accountDTO , AccountDTO.class))
                .toList();
    }

}
