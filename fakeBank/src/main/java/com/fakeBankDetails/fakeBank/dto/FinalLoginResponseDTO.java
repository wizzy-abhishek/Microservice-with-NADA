package com.fakeBankDetails.fakeBank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalLoginResponseDTO {
    private String id ;
    private String accessToken ;
    private String refreshToken ;
}
