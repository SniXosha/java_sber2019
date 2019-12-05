package ru.myhw.task9;

import ru.sbt.shop.Message;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientProxyFactory implements InvocationHandler {
    private final String host;
    private final int port;

    public ClientProxyFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @SuppressWarnings("unchecked")
    public <T> T proxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{clazz},
                new ClientProxyFactory(host, port));
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Message message = new Message(method.getName(), objects);
        return process(message);
    }

    private Object process(Message message) throws IOException, ClassNotFoundException {
        return new RemoteMethodCaller(host, port).call(message);
    }
}
