package com.fakeSocialMedia.fakeSocialMedia.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Document
public class Post {

    @Id
    private long id ;

    private Date postDate;

    private String caption ;

    private Account account;
}
