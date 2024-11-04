package com.fakeBankDetails.fakeBank.service;

import com.fakeBankDetails.fakeBank.dto.AccountHoldersDetailsDTO;
import com.fakeBankDetails.fakeBank.entity.AccountHoldersDetails;
import com.fakeBankDetails.fakeBank.repository.AccountHolderDetailsRepo;
import com.fakeBankDetails.fakeBank.service.interfaces.AccountHolderDetailService;
import com.fakeBankDetails.fakeBank.utils.IndianStateMappingCode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountHolderDetailsService implements AccountHolderDetailService {

    private final ModelMapper modelMapper ;
    private final AccountHolderDetailsRepo holderDetailsRepo ;
    private final EmailService emailService ;

    public AccountHolderDetailsService(ModelMapper modelMapper, AccountHolderDetailsRepo holderDetailsRepo, EmailService emailService) {
        this.modelMapper = modelMapper;
        this.holderDetailsRepo = holderDetailsRepo;
        this.emailService = emailService;
    }

    @Transactional
    public AccountHoldersDetailsDTO createNewAccount(AccountHoldersDetailsDTO accountHoldersDetailsDTO){

        AccountHoldersDetails accountHoldersDetails = modelMapper.map(accountHoldersDetailsDTO , AccountHoldersDetails.class);
        accountHoldersDetails.setAccountNumber(IndianStateMappingCode
                .getStateCode(accountHoldersDetails.getState()) + generateUniqueLongID());
        accountHoldersDetails.setBalance(0.00F);

        AccountHoldersDetails savedAHD = holderDetailsRepo.save(accountHoldersDetails);
        String subject = "Your account created" ;
        String message = "You account has been created at " + savedAHD.getBranch() + ", " + savedAHD.getState() + "."
                + "\nYour account number is " + savedAHD.getAccountNumber() + " and this is " + savedAHD.getAccountType() +" .\n" +
                "Thanks , We assure our journey will be fabulous ." ;
        emailService.sendOTPinMail(savedAHD.getEmail() , subject , message );

        return modelMapper.map( savedAHD , AccountHoldersDetailsDTO.class);
    }

    private long generateUniqueLongID() {
        UUID uuid = UUID.randomUUID();
        long uniqueLongID = uuid.getMostSignificantBits();
        return uniqueLongID < 0 ? -uniqueLongID : uniqueLongID;
    }

}
