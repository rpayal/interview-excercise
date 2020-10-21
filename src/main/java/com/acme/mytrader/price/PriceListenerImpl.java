package com.acme.mytrader.price;

import com.acme.mytrader.enums.Stock;
import com.acme.mytrader.execution.ExecutionService;

public class PriceListenerImpl implements PriceListener {

    public static final Stock STOCK_TO_CHECK = Stock.valueOf("IBM");

    private final ExecutionService executionService;

    public PriceListenerImpl(ExecutionService executionService) {
        this.executionService = executionService;
    }

    @Override
    public void priceUpdate(String security, double price) {
        if (security.equals(STOCK_TO_CHECK.getStockTicker())) {
            if (price <= STOCK_TO_CHECK.getBuyPriceThreshold()) {
                executionService.buy(security, price, STOCK_TO_CHECK.getBuyVolume());
            } else if (price >= STOCK_TO_CHECK.getSellPriceThreshold()){
                // Implement SELL stock
            }
        }
    }
}
