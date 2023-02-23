package cloud.yy.springcloud.controller;

import com.yy.springcloud.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    //调用支付订单服务端的ip+端口号
    public static final String PAYMENT_URL = "http://localhost:8001/api";

    @Autowired
    private RestTemplate restTemplate;

    //创建支付订单的接口
    @GetMapping("/consumer/payment/create")
    public Payment create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, Payment.class);
    }

    //获取id获取支付订单
    @GetMapping("/consumer/payment/get/{id}")
    public Payment getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, Payment.class);
    }
}
