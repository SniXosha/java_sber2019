package ru.myhw.task9.server;

public class ServerProxyFactory {
    public void expose(Object processor, int port) {
        Server.of(port, SocketMethodCallProcessor.of(processor)).run();
    }
}
