package com.fakeSocialMedia.fakeSocialMedia.repository;

import com.fakeSocialMedia.fakeSocialMedia.entity.Account;
import com.fakeSocialMedia.fakeSocialMedia.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends MongoRepository<Post , Long> {

    List<Post> findAllPostByAccount(Account account);
}
