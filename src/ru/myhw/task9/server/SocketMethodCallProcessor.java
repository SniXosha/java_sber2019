package ru.myhw.task9.server;

import ru.sbt.shop.MethodCallMessage;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Arrays;

import static ru.myhw.task9.socketutils.Utils.readFromSocket;
import static ru.myhw.task9.socketutils.Utils.writeToSocket;

public class SocketMethodCallProcessor implements SocketProcessor {

    private final Object processor;

    private SocketMethodCallProcessor(Object processor) {
        this.processor = processor;
    }

    public static SocketMethodCallProcessor of(Object processor) {
        return new SocketMethodCallProcessor(processor);
    }

    @Override
    public void process(Socket socket) {
        MethodCallMessage message = getMessage(socket);
        if (message == null) return;
        Object result = invokeMethod(message);
        if (result == null) return;
        sendMessage(socket, result);
    }

    private Object invokeMethod(MethodCallMessage message) {
        System.out.println(message);
        Class<?>[] classes = Arrays.stream(message.getArgs()).map(Object::getClass).toArray(Class<?>[]::new);
        try {
            Method method = processor.getClass().getMethod(message.getMethodName(), classes);
            return method.invoke(processor, message.getArgs());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendMessage(Socket socket, Object obj) {
        try {
            writeToSocket(socket, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MethodCallMessage getMessage(Socket socket) {
        try {
            return (MethodCallMessage) readFromSocket(socket);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
