package ru.myhw.task9.client;

import ru.sbt.shop.Order;
import ru.sbt.shop.Result;
import ru.sbt.shop.ShopClient;

import java.io.IOException;

public class RemoteShopClient implements ShopClient {

    private final String host;
    private final int port;

    public RemoteShopClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Result process(Order order) {
        Result result = null;
        try {
            result = (Result) new RemoteMethodCaller(host, port).call(order);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
