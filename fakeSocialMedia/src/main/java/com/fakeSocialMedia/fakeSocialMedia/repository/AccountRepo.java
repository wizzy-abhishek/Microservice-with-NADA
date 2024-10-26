package com.fakeSocialMedia.fakeSocialMedia.repository;

import com.fakeSocialMedia.fakeSocialMedia.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends MongoRepository<Account , String> {
    boolean existsByEmail(String email);
    boolean existsByMobile(long mobile);

}
