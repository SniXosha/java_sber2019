package ru.myhw.task1.app;

import ru.myhw.task1.app.tradetypes.Trade;

public class Application {

    public static void main(String[] args) {
        String filename = args[0];
        Config conf = ConfigLoader.loadFromFile(filename);
        if (conf == null) {
            System.out.println("Couldnt parse config");
            return;
        }
        Trade trade = Trade.createTrade(conf.getType(), conf.getPriceValue());
        if (trade == null) {
            System.out.println("Couldnt create trade");
            return;
        }
        System.out.println(trade.getPrice());
    }
}
