package com.fakeBankDetails.fakeBank.controller;

import com.fakeBankDetails.fakeBank.dto.AccountHoldersDetailsDTO;
import com.fakeBankDetails.fakeBank.dto.DepositDTO;
import com.fakeBankDetails.fakeBank.dto.TransferAmountDTO;
import com.fakeBankDetails.fakeBank.dto.WithdrawalDTO;
import com.fakeBankDetails.fakeBank.service.interfaces.BanksBankingOperationServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/operations")
public class BanksBankingOperationController {

    private final BanksBankingOperationServiceInterface bankingOperationServiceInterface ;

    public BanksBankingOperationController(BanksBankingOperationServiceInterface bankingOperationServiceInterface) {
        this.bankingOperationServiceInterface = bankingOperationServiceInterface;
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<String> checkBalance(@PathVariable String accountNumber){
        double balance = bankingOperationServiceInterface.checkBalance(accountNumber);
        String message = "Balance of account number " + accountNumber + " is " + balance ;
        return ResponseEntity.ok(message);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<AccountHoldersDetailsDTO> withdrawal(@RequestBody WithdrawalDTO withdrawalDTO ){
        return ResponseEntity.ok(bankingOperationServiceInterface
                .withdrawal(withdrawalDTO.getAccount() , withdrawalDTO.getAmount()));
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountHoldersDetailsDTO> deposit(@RequestBody DepositDTO depositDTO ){
        return ResponseEntity.ok(bankingOperationServiceInterface
                .deposit( depositDTO.getAmount() , depositDTO.getAccount()));
    }

    @PostMapping("/transfer")
    public ResponseEntity<AccountHoldersDetailsDTO> transfer(@RequestBody TransferAmountDTO transferAmountDTO){
        return ResponseEntity.ok(bankingOperationServiceInterface
                .transfer(transferAmountDTO.getAmount() , transferAmountDTO.getFromAccount() , transferAmountDTO.getToAccount()));
    }

}
