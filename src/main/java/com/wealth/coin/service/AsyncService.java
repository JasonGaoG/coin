package com.wealth.coin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

    // 指定使用bean name为binanceExecutor的线程池
    @Async("binanceExecutor")
    public String binanceExecutor(String message) {
        log.info("binanceExecutor, message={}", message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("do something error: ", e);
        }
        return message;
    }
}
