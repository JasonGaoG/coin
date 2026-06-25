package com.wealth.coin.runner;

import com.wealth.coin.coin.binance.clients.SpotWebSocketClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Order(2)
@Slf4j
public class InitRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("MyCommandLineRunner order is 2");
        SpotWebSocketClient.getInstance().getTicker("BTCUSDT");
    }
}
