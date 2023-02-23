package com.yy.springcloud;


import com.yy.springcloud.config.AudienceConfig;
import com.yy.springcloud.pojo.Payment;
import com.yy.springcloud.service.PaymentService;
import com.yy.springcloud.utils.JwtCreator;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AudienceConfig audienceConfig;

    @Test
    public void test(){
        Payment payment = paymentService.queryById(1L);
        System.out.println(payment);
    }

    @Test
    public void save(){
        Payment payment = Payment.builder()
                .userId(12220033L)
                .cowry(60d)
                .status(0)
                .channel("nowPay_offical_13")
                .addTime(new Date())
                .orderId("122200021663237458923")
                .orderType(1)
                .productId(2)
                .staticAmount(0.01d)
                .build();
        paymentService.create(payment);
    }


    @Test
    public void jwtTest(){
        String token = JwtCreator.createJwt("123343", "jack", 1, audienceConfig);
        System.out.println(token);

        Claims claims = JwtCreator.parseJwt(token, audienceConfig.getBase64Secret());
        System.out.println(claims.toString());
    }
}
