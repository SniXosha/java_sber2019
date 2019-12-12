package ru.myhw.task9;

import ru.myhw.task9.server.ServerProxyFactory;

public class ServerMain {
    public static void main(String[] args) {
        ServerProxyFactory factory = new ServerProxyFactory();
        factory.expose(new ShopClientImpl(), 1919);
    }
}
