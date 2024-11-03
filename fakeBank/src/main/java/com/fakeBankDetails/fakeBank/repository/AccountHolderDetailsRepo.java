package com.fakeBankDetails.fakeBank.repository;

import com.fakeBankDetails.fakeBank.entity.AccountHoldersDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHolderDetailsRepo extends JpaRepository<AccountHoldersDetails , String> {

}
