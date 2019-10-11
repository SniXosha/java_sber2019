package ru.myhw.task5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;

public class TimeMeasureProxy implements InvocationHandler {

    private final Object delegate;

    TimeMeasureProxy(Object delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(T delegate) {

        Class<?> clazz = delegate.getClass();
        ArrayList<Class<?>> allInterfaces = new ArrayList<>();
        while (clazz != null) {
            Class<?>[] interfaces = clazz.getInterfaces();
            allInterfaces.addAll(Arrays.asList(interfaces));
            clazz = clazz.getSuperclass();
        }

        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                allInterfaces.toArray(new Class<?>[allInterfaces.size()]),
                new TimeMeasureProxy(delegate));
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = method.invoke(delegate, objects);
        long endTime = System.currentTimeMillis();
        System.out.printf("Time: %d\n", endTime - startTime);
        return result;
    }
}
