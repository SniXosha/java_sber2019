package ru.myhw.task1.trading.tradetypes;

public enum TradeType implements TradeFactory {
    FX_SPOT {
        @Override
        public Trade create(double price) {
            return new FxSpot(price);
        }
    },
    BOND {
        @Override
        public Trade create(double price) {
            return new Bond(price);
        }
    },
    COMMODITY_SPOT {
        @Override
        public Trade create(double price) {
            return new CommoditySpot(price);
        }
    },
    IR_SWAP {
        @Override
        public Trade create(double price) {
            return new IrSwap(price);
        }
    }
}
