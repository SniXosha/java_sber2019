package ru.myhw.task1.trading.loader;

import ru.myhw.task1.trading.parser.Parser;
import ru.myhw.task1.trading.parser.ParserException;
import ru.myhw.task1.trading.tradetypes.Trade;
import ru.myhw.task1.trading.tradetypes.TradeType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TradeFileLoader implements TradeLoader {

    private final String filename;

    private TradeFileLoader(String filename) {
        this.filename = filename;
    }

    public static TradeFileLoader create(String filename) {
        return new TradeFileLoader(filename);
    }

    @Override
    public Trade load() throws TradeLoaderException {
        TradeInfo tradeInfo = loadTradeInfo();
        return TradeType
                .valueOf(tradeInfo.getType())
                .create(tradeInfo.getPriceValue());
    }

    private TradeInfo loadTradeInfo() throws TradeLoaderException {
        try {
            return TradeInfo.from(Parser.parse(new FileInputStream(filename)));
        } catch (FileNotFoundException | ParserException e) {
            throw new TradeLoaderException(e.getMessage());
        }
    }
}
