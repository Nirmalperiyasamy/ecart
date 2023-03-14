package com.example.product.service;

import com.example.product.dao.Wallet;
import com.example.product.dto.WalletDto;
import com.example.product.repository.WalletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    protected WalletRepo walletRepo;


    public ResponseEntity<?> addWallet(String uid) {

        Wallet wallet = new Wallet();
        wallet.setCustomer(uid);
        wallet.setBalance(5000);

        walletRepo.save(wallet);

        return ResponseEntity.ok("Wallet added");
    }
}
