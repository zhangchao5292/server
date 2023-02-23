package com.yy.springcloud.dao;


import com.yy.springcloud.pojo.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentDao {

    int create(Payment payment);

    Payment queryById(@Param("id")long id);

}