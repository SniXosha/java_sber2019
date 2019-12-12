package ru.myhw.task1.trading.tradetypes;

public interface TradeFactory {
    Trade create(double price);
}
