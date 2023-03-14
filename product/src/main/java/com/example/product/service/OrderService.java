package com.example.product.service;

import com.example.product.dao.Wallet;
import com.example.product.exception.CustomException;
import com.example.product.dao.OrderRecipt;
import com.example.product.dao.Product;
import com.example.product.dto.OrderDto;
import com.example.product.repository.OrderRepo;
import com.example.product.repository.ProductRepo;
import com.example.product.repository.WalletRepo;
import com.example.product.util.Messages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    protected OrderRepo orderRepo;

    @Autowired
    protected ProductRepo productRepo;

    @Autowired
    protected WalletRepo walletRepo;

    public OrderDto orderProduct(OrderDto dto) {

        countLimit(dto);

        OrderRecipt orderRecipt = new OrderRecipt();
        BeanUtils.copyProperties(dto, orderRecipt);

        orderRecipt.setProductOwnerUid(ownerUid(dto));

        orderRecipt.setPrice(totalAmount(dto));

        orderRecipt.setDate(orderTime());

        orderRepo.save(orderRecipt);
        BeanUtils.copyProperties(orderRecipt, dto);

        return dto;
    }

    private String orderTime() {
        Date date = new Date(System.currentTimeMillis());
        return date.toString();
    }

    private String ownerUid(OrderDto dto) {
        Product product = productRepo.findByProduct(dto.getProduct());
        return product.getUid();
    }


    private Integer totalAmount(OrderDto dto) {

        Product product = productRepo.findByProduct(dto.getProduct());

        Wallet wallet = walletRepo.findByCustomer(product.getUid());

        if (dto.getCount() * product.getPrice() < wallet.getBalance()) {
            wallet.setBalance(wallet.getBalance() - dto.getCount() * product.getPrice());
            walletRepo.save(wallet);
            return dto.getCount() * product.getPrice();
        } else {
            throw new CustomException(Messages.INSUFFICIENT_BALANCE);

        }
    }

    void countLimit(OrderDto dto) {
        Product product = productRepo.findByProduct(dto.getProduct());
        if (product.getCount() <= dto.getCount()) {
            throw new CustomException(Messages.SOLD);
        } else {
            product.setCount(product.getCount() - dto.getCount());
            productRepo.save(product);
        }
    }
}
