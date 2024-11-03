package com.fakeBankDetails.fakeBank.repository;

import com.fakeBankDetails.fakeBank.entity.AccountHoldersDetails;
import com.fakeBankDetails.fakeBank.entity.UserEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity , String> {

    Optional<UserEntity> findByEmailIgnoreCase(String email);
     boolean existsByEmailIgnoreCase(String email);
    List<AccountHoldersDetails> findAllByEmail(String email);
}
