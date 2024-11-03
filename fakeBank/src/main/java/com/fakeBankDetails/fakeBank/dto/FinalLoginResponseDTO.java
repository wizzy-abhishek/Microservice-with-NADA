package com.fakeBankDetails.fakeBank.dto;

import com.fakeBankDetails.fakeBank.entity.AccountHoldersDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalLoginResponseDTO {
    private String id ;
    private String accessToken ;
    private String refreshToken ;
    private List<AccountHoldersDetailsDTO> relatedAccounts = new ArrayList<>();
}
