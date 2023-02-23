package com.yy.springcloud.service;

import com.yy.springcloud.config.CacheKeys;
import com.yy.springcloud.dao.PaymentDao;
import com.yy.springcloud.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentImpl implements PaymentService{

    @Autowired
    PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

//    @Cacheable ： 触发将数据保存到缓存的操作；
//    @CacheEvict : 触发将数据从缓存删除的操作；
//    @CachePut ： 不影响方法执行更新缓存；
//    @Cacheing： 组合以上多个操作；
//    @CacheConfig： 在类级别共享缓存的相同配置

    @Cacheable(value = CacheKeys.DETAIL_LIVE_ROOM_CACHE_PREFIX, key = "#id")
    @Override
    public Payment queryById(long id) {
        return paymentDao.queryById(id);
    }

    @Async("newAsyncExecutor")
    @Override
    public String async(Long userId) {
        try {
            Thread.sleep(5000);
            log.info("aysnc "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    //unless:否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存,返回值是null的不缓存
//    @Cacheable(value = CacheKeys.DETAIL_LIVE_ROOM_CACHE_PREFIX, key = "#id", unless = "#result == null")
//    @Override
//    public Payment queryById(long id) {
//        return paymentDao.queryById(id);
//    }
}
