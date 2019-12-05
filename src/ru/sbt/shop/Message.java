package ru.sbt.shop;

import java.io.Serializable;
import java.util.Arrays;

public class Message implements Serializable {
    private final String methodName;
    private final Object[] args;

    public Message(String methodName, Object[] args) {
        this.methodName = methodName;
        this.args = args;
    }


    public String getMethodName() {
        return methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "Message{" +
                "methodName='" + methodName + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
