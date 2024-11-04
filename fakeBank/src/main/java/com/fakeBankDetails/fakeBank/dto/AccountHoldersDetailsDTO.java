package com.fakeBankDetails.fakeBank.dto;

import com.fakeBankDetails.fakeBank.enums.AccountType;
import com.fakeBankDetails.fakeBank.enums.IndianState;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountHoldersDetailsDTO {

    private String accountNumber ;

    private String name ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double balance ;

    private String email ;

    private long mobile ;

    private String branch ;

    private IndianState state ;

    private AccountType accountType ;

    private boolean apiAllowed ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> transactions ;

}