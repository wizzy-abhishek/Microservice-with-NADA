package com.fakeBankDetails.fakeBank.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WithdrawalDTO {

    private String account;
    private int amount;
}
