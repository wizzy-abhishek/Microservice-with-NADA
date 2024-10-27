package com.fakeBankDetails.fakeBank.entity;

import jakarta.persistence.*;
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
    @Column(updatable = false)
    private String accountNumber;

    private String name;

    private BigDecimal balance;

    private long mobile;

    private String branch;

    private boolean apiAllowed;

    @ElementCollection
    private List<String> transactions ;

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    private UserEntity user;

    public AccountHoldersDetails() {
        this.transactions = new ArrayList<>();
    }
}
