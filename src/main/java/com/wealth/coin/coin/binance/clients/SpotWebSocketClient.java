package com.wealth.coin.coin.binance.clients;

import com.binance.connector.client.common.configuration.SignatureConfiguration;
import com.binance.connector.client.common.websocket.configuration.WebSocketClientConfiguration;
import com.binance.connector.client.spot.websocket.api.SpotWebSocketApiUtil;
import com.binance.connector.client.spot.websocket.api.api.SpotWebSocketApi;
import com.binance.connector.client.spot.websocket.api.model.AccountStatusRequest;
import com.binance.connector.client.spot.websocket.api.model.AccountStatusResponse;
import com.binance.connector.client.spot.websocket.api.model.TickerRequest;
import com.binance.connector.client.spot.websocket.api.model.TickerResponse;
import org.eclipse.jetty.client.ProxyConfiguration;
import org.eclipse.jetty.client.Socks4Proxy;

import java.util.concurrent.CompletableFuture;

public class SpotWebSocketClient {
    private static SpotWebSocketClient instance;
    private static SpotWebSocketApi api;
    private SpotWebSocketClient(){};

    public static SpotWebSocketClient getInstance() {
        if (instance == null) {
            WebSocketClientConfiguration clientConfiguration = SpotWebSocketApiUtil.getClientConfiguration();
            clientConfiguration.setAutoLogon(true);
            ProxyConfiguration.Proxy proxy = new Socks4Proxy("127.0.0.1",7890);
            clientConfiguration.setWebSocketProxy(proxy);
            SignatureConfiguration signatureConfiguration = new SignatureConfiguration();
            signatureConfiguration.setApiKey("owIiHTxF1hGaxK3OPJ6p5LSxcTYZ0naf0QTUH5w6Au5FNWNfC3boQZzAfROuPloo");
            signatureConfiguration.setPrivateKeyPass("admin123");
            signatureConfiguration.setPrivateKey("-----BEGIN ENCRYPTED PRIVATE KEY-----" +
                    "MIGjMF8GCSqGSIb3DQEFDTBSMDEGCSqGSIb3DQEFDDAkBBCDqABeCaPbg+1JLb1n" +
                    "Bpe3AgIIADAMBggqhkiG9w0CCQUAMB0GCWCGSAFlAwQBKgQQW1Z4sUGnuAwYQdDY" +
                    "e1JcvgRAhLnBgkTQkhtFW8FYuyULdaa69DvdBltoyqBt+UMXWJfSp7eqEZVwAh3+" +
                    "qWql4ND24/v3CuNO7H5OMQnng2Jw4A==" +
                    "-----END ENCRYPTED PRIVATE KEY-----");
//        signatureConfiguration.setSecretKey("Max41VlXUeWx7kkIrE7hzLEz2B7wKJAa3jBc2pHDGm2zbUHHnXCeIyglMMNYbScs");
            clientConfiguration.setSignatureConfiguration(signatureConfiguration);
            api = new SpotWebSocketApi(clientConfiguration);
            instance = new SpotWebSocketClient();
        }
        return instance;
    }

    public AccountStatusResponse getAccountStatus() {
        AccountStatusRequest accountStatusRequest = new AccountStatusRequest();
        CompletableFuture<AccountStatusResponse> future = api.accountStatus(accountStatusRequest);
        AccountStatusResponse response = future.join();

        return response;
    }

    public TickerResponse getTicker(String symbol) {
        TickerRequest tickerRequest = new TickerRequest();
        tickerRequest.symbol(symbol);
        CompletableFuture<TickerResponse> future = api.ticker(tickerRequest);
        TickerResponse response = future.join();
        System.out.println(response);
        return response;
    }
}
