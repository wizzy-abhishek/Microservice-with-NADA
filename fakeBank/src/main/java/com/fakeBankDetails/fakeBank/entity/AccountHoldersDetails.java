package com.fakeBankDetails.fakeBank.entity;

import com.fakeBankDetails.fakeBank.enums.Bank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class AccountHoldersDetails {

    @Id
    @Column(updatable = false )
    private String accountNumber ;

    private String name ;

    private BigDecimal balance ;

    private long mobile ;

    private Bank bank ;

    private String branch ;

    private boolean apiAllowed ;

    private List<String> transactions;

    public AccountHoldersDetails() {
        this.transactions = new ArrayList<>();
    }
}
