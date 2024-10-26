package com.fakeSocialMedia.fakeSocialMedia.entity;

import com.fakeSocialMedia.fakeSocialMedia.enums.SocialMedia;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Document
public class Account {

    private SocialMedia socialMedia ;

    @Id
    private String accountName ;

    @Indexed(unique = true)
    private String email ;

    @Indexed(unique = true)
    private long mobile ;

    @ToString.Exclude
    private List<Post> posts ;

}
