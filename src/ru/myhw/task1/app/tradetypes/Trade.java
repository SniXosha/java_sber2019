package ru.myhw.task1.app.tradetypes;

public abstract class Trade {
    private final double price;

    Trade(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public static Trade createTrade(String type, double value) {
        switch (type) {
            case "FX_SPOT":
                return new FxSpot(value);
            case "BOND":
                return new Bond(value);
            case "COMMODITY_SPOT":
                return new CommoditySpot(value);
            case "IR_SWAP":
                return new IrSwap(value);
        }
        return null;
    }
}
