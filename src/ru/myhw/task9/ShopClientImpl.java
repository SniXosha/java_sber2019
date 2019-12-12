package ru.myhw.task9;

import ru.sbt.shop.Order;
import ru.sbt.shop.Result;
import ru.sbt.shop.ShopClient;

public class ShopClientImpl implements ShopClient {
    @Override
    public Result process(Order order) {
        return new Result(order.getPrice() > 100);
    }
}
