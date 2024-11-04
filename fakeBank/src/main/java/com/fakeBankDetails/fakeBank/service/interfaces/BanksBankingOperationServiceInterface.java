package com.fakeBankDetails.fakeBank.service.interfaces;

import com.fakeBankDetails.fakeBank.dto.AccountHoldersDetailsDTO;

public interface BanksBankingOperationServiceInterface {

    AccountHoldersDetailsDTO withdrawal(int amount , String account);
    AccountHoldersDetailsDTO deposit(int amt , String account) ;
    float checkBalance(String account);
    AccountHoldersDetailsDTO transfer(int amt , String from , String to);

}
