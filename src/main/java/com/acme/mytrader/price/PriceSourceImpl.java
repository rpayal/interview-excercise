package com.acme.mytrader.price;

import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PriceSourceImpl implements PriceSource {
    @Getter
    private final List<PriceListener> priceListeners = new CopyOnWriteArrayList<>();

    @Override
    public void addPriceListener(PriceListener listener) {
        this.priceListeners.add(listener);
    }

    @Override
    public void removePriceListener(PriceListener listener) {
        this.priceListeners.remove(listener);
    }

    public void setPrice(String security, double price) {
        for(PriceListener listener: this.priceListeners) {
            if (price >= 0.0) {
                listener.priceUpdate(security, price);
            } else {
                listener.priceUpdate(security, 0.0);
            }
        }
    }
}
