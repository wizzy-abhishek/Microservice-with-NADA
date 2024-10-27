package com.fakeBankDetails.fakeBank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.aspectj.weaver.Lint;
import org.w3c.dom.stylesheets.LinkStyle;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountHoldersDetailsDTO {

    private String accountNumber ;

    private String name ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal balance ;

    private long mobile ;

    private String branch ;

    private boolean apiAllowed ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> transactions;

}