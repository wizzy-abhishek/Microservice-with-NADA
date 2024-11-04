package com.fakeBankDetails.fakeBank.service.interfaces;

import com.fakeBankDetails.fakeBank.dto.AccountHoldersDetailsDTO;

public interface BanksBankingOperationServiceInterface {

    AccountHoldersDetailsDTO withdrawal( String account , int amount );
    AccountHoldersDetailsDTO deposit(int amt , String account) ;
    double checkBalance(String account);
    AccountHoldersDetailsDTO transfer(int amt , String from , String to);

}
