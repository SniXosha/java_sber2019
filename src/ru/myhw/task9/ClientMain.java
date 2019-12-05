package ru.myhw.task9;

import ru.sbt.shop.Order;
import ru.sbt.shop.Result;

public class ClientMain {

    public static void main(String[] args) {
        ClientProxyFactory factory = new ClientProxyFactory("192.168.43.133", 1919);
        ShopClient client = factory.proxy(ShopClient.class);
        Result res = client.process(new Order("SberHoody", 300));
        System.out.println(res);
    }
}
