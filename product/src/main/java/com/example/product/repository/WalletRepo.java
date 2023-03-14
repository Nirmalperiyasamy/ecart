package com.example.product.repository;

import com.example.product.dao.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer> {

    Wallet findByCustomer(String customerUid);
}
