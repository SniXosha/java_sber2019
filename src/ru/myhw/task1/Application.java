package ru.myhw.task1;

import ru.myhw.task1.trading.loader.TradeFileLoader;
import ru.myhw.task1.trading.loader.TradeLoaderException;
import ru.myhw.task1.trading.tradetypes.Trade;

public class Application {

    public static void main(String[] args) throws TradeLoaderException {
        String filename = args[0];
        Trade trade = TradeFileLoader.create(filename).load();
        System.out.println(trade.getPrice());
    }
}
