package com.fakeSocialMedia.fakeSocialMedia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PostDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date postDate;

    private String caption ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private AccountDTO account ;

}
