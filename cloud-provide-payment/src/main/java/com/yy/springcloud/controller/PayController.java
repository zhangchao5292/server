package com.yy.springcloud.controller;


import com.yy.springcloud.pojo.Payment;
import com.yy.springcloud.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "测试")
@RestController
@RequestMapping(value = "/api/v1")
public class PayController {

    @Autowired
    private PaymentService paymentService;

    @ApiOperation(value = "查询下单信息", notes = "查询数据库订单信息")
    @PostMapping("/payInfo")
    public Payment payInfo(@ApiParam(name = "userId",value = "用户id", required = true) @RequestParam(required = true) Long userId) {
        return paymentService.queryById(userId);
    }

    @ApiOperation(value = "异步方法测试", notes = "async")
    @PostMapping("/async")
    public String async(@ApiParam(name = "userId",value = "用户id", required = true) @RequestParam(required = true) Long userId) {
        return paymentService.async(userId);
    }


}
