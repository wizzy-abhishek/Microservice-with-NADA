package com.fakeBankDetails.fakeBank.repository;

import com.fakeBankDetails.fakeBank.entity.AccountHoldersDetails;
import jakarta.persistence.LockModeType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHolderDetailsRepo extends JpaRepository<AccountHoldersDetails , String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<AccountHoldersDetails> findByAccountNumber(String accNum);
}
