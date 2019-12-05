package ru.myhw.task9;

import ru.sbt.shop.Order;
import ru.sbt.shop.Result;

public interface ShopClient {
    Result process(Order order);
}
