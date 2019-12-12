package ru.myhw.task1.trading.tradetypes;

public abstract class Trade {
    private final double price;

    Trade(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
