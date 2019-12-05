package ru.sbt.shop;

import java.io.Serializable;

public class Order implements Serializable {
    private final String name;
    private final int price;

    public Order(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
