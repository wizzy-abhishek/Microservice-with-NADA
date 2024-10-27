package com.fakeSocialMedia.fakeSocialMedia.dto;

import com.fakeSocialMedia.fakeSocialMedia.enums.SocialMedia;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccountDTO {

    @NotNull
    private SocialMedia socialMedia ;

    @NotNull(message = "Account name cant be null")
    private String accountName ;

    @Email
    private String email ;

    @NotNull
    private long mobile ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<PostDTO> posts ;

    public AccountDTO() {
        this.posts = new ArrayList<>() ;
    }

}
