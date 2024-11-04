package com.fakeBankDetails.fakeBank.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DepositDTO {
    private String account;
    private int amount;
}
