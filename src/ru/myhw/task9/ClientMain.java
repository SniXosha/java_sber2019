package ru.myhw.task9;

import ru.myhw.task9.client.ClientProxyFactory;
import ru.sbt.shop.Order;
import ru.sbt.shop.Result;
import ru.sbt.shop.ShopClient;

public class ClientMain {

    public static void main(String[] args) {
        ClientProxyFactory factory = new ClientProxyFactory("localhost", 1919);
        ShopClient client = factory.proxy(ShopClient.class);
        Result res = client.process(new Order("SberHoody", 300));
        System.out.println(res);
    }
}
