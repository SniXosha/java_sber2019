package ru.myhw.task1.trading.loader;

import ru.myhw.task1.trading.tradetypes.Trade;

public interface TradeLoader {
    Trade load() throws TradeLoaderException;
}
