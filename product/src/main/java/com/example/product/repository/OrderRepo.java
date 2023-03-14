package com.example.product.repository;

import com.example.product.dao.OrderRecipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderRecipt, Integer> {

}
