package com.fakeBankDetails.fakeBank.service;

import com.fakeBankDetails.fakeBank.dto.AccountHoldersDetailsDTO;
import com.fakeBankDetails.fakeBank.entity.AccountHoldersDetails;
import com.fakeBankDetails.fakeBank.repository.AccountHolderDetailsRepo;
import com.fakeBankDetails.fakeBank.utils.IndianStateMappingCode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountHolderDetailsService {

    private final ModelMapper modelMapper ;
    private final AccountHolderDetailsRepo holderDetailsRepo ;

    public AccountHolderDetailsService(ModelMapper modelMapper, AccountHolderDetailsRepo holderDetailsRepo) {
        this.modelMapper = modelMapper;
        this.holderDetailsRepo = holderDetailsRepo;
    }

    public AccountHoldersDetailsDTO createNewAccount(AccountHoldersDetailsDTO accountHoldersDetailsDTO){

        AccountHoldersDetails accountHoldersDetails = modelMapper.map(accountHoldersDetailsDTO , AccountHoldersDetails.class);
        accountHoldersDetails.setAccountNumber(IndianStateMappingCode
                .getStateCode(accountHoldersDetails.getState()) + generateUniqueLongID());


        return new AccountHoldersDetailsDTO();
    }

    public static long generateUniqueLongID() {
        UUID uuid = UUID.randomUUID();
        long uniqueLongID = uuid.getMostSignificantBits();
        return uniqueLongID < 0 ? -uniqueLongID : uniqueLongID;
    }

}
