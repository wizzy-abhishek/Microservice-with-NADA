package com.fakeBankDetails.fakeBank.dto;

import com.fakeBankDetails.fakeBank.enums.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private String email ;

    private long mobile ;

    private Set<Roles> roles ;
}
