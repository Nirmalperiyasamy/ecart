package com.example.product.controller;

import com.example.product.dto.WalletDto;
import com.example.product.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.product.util.Constants.ADD_WALLET;
import static com.example.product.util.Constants.WALLET;

@RestController
@RequestMapping(WALLET)
@Slf4j
public class WalletController {
    @Autowired
    WalletService walletService;

    @PostMapping(ADD_WALLET)
    public ResponseEntity<?> wallet(@RequestBody String uid) {
        log.info(uid);
        return walletService.addWallet(uid);
    }
}
