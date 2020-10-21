package com.acme.mytrader.price;

public interface PriceListener {
    void priceUpdate(String security, double price);
}
