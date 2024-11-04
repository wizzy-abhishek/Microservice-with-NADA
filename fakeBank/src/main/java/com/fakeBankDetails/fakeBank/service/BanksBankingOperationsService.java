package com.fakeBankDetails.fakeBank.service;

import com.fakeBankDetails.fakeBank.dto.AccountHoldersDetailsDTO;
import com.fakeBankDetails.fakeBank.entity.AccountHoldersDetails;
import com.fakeBankDetails.fakeBank.exceptions.InsufficientFundException;
import com.fakeBankDetails.fakeBank.exceptions.ResourceNotFoundException;
import com.fakeBankDetails.fakeBank.repository.AccountHolderDetailsRepo;
import com.fakeBankDetails.fakeBank.service.interfaces.BanksBankingOperationServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BanksBankingOperationsService implements BanksBankingOperationServiceInterface {

    private final AccountHolderDetailsRepo accountHolderDetailsRepo ;
    private final ModelMapper modelMapper ;

    public BanksBankingOperationsService(AccountHolderDetailsRepo accountHolderDetailsRepo, ModelMapper modelMapper) {
        this.accountHolderDetailsRepo = accountHolderDetailsRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public AccountHoldersDetailsDTO withdrawal(String account , int amount){

        AccountHoldersDetails accountHoldersDetails = accountHolderDetailsRepo.findByAccountNumber(account)
                .orElseThrow(() -> new ResourceNotFoundException("NO ACCOUNT FOUND"));
        if(accountHoldersDetails.getBalance() < amount){
            throw new InsufficientFundException("Insufficient Fund");
        }
        double balance = accountHoldersDetails.getBalance() - amount ;
        accountHoldersDetails.setBalance(balance);
        AccountHoldersDetails savedAccountHoldersDetails = accountHolderDetailsRepo.save(accountHoldersDetails);

        return modelMapper.map(savedAccountHoldersDetails , AccountHoldersDetailsDTO.class);
    }

    @Override
    @Transactional
    public AccountHoldersDetailsDTO deposit(int amt , String account){
        AccountHoldersDetails accountHoldersDetails = accountHolderDetailsRepo.findByAccountNumber(account)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        double newBalance = accountHoldersDetails.getBalance() + amt ;
        accountHoldersDetails.setBalance(newBalance);
        AccountHoldersDetails savedAccountHoldersDetails = accountHolderDetailsRepo.save(accountHoldersDetails);

        return modelMapper.map(savedAccountHoldersDetails , AccountHoldersDetailsDTO.class);
    }

    @Override
    @Transactional
    public AccountHoldersDetailsDTO transfer(int amt , String from , String to){
        AccountHoldersDetails fromAccount = accountHolderDetailsRepo.findByAccountNumber(from)
                .orElseThrow(() -> new ResourceNotFoundException("Sender's account not found"));
        double senderBalance = fromAccount.getBalance();
        if(senderBalance < amt){
            throw new InsufficientFundException("Sender Balance insufficient.");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amt);
        AccountHoldersDetails toAccount = accountHolderDetailsRepo.findByAccountNumber(to)
                .orElseThrow(() -> new ResourceNotFoundException("Account to be sent not found"));
        toAccount.setBalance(toAccount.getBalance() + amt);
        AccountHoldersDetails savedFromAccount = accountHolderDetailsRepo.save(fromAccount);
        accountHolderDetailsRepo.save(toAccount);
        return modelMapper.map(savedFromAccount , AccountHoldersDetailsDTO.class);
    }

    @Override
    @Transactional()
    public double checkBalance(String account){
        AccountHoldersDetails accountHoldersDetails =  accountHolderDetailsRepo.findByAccountNumber(account)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        return accountHoldersDetails.getBalance();
    }

}
