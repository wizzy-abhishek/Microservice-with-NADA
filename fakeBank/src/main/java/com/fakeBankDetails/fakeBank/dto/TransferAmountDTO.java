package com.fakeBankDetails.fakeBank.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransferAmountDTO {

    private String fromAccount;
    private String toAccount;
    private int amount;
}
