package com.yy.springcloud.service;


import com.yy.springcloud.pojo.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment queryById(long id);

    String async(Long userId);
}