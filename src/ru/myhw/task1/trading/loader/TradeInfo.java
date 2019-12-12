package ru.myhw.task1.trading.loader;

import java.util.Map;
import java.util.Objects;

class TradeInfo {
    private final String type;
    private final double priceValue;

    private TradeInfo(String type, double priceValue) {
        this.type = type;
        this.priceValue = priceValue;
    }

    public static TradeInfo from(Map<String, String> map) {
        String type = Objects.requireNonNull(map.get("type"));
        double price = Double.parseDouble(Objects.requireNonNull(map.get("price")));
        return new TradeInfo(type, price);
    }

    public String getType() {
        return type;
    }

    public double getPriceValue() {
        return priceValue;
    }
}
