package ru.myhw.task1.app;

public class Config {
    private final String type;
    private final double priceValue;

    public Config(String type, double priceValue) {
        this.type = type;
        this.priceValue = priceValue;
    }

    public String getType() {
        return type;
    }

    public double getPriceValue() {
        return priceValue;
    }
}
