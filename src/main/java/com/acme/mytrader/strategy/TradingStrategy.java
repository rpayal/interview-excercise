package com.acme.mytrader.strategy;

import com.acme.mytrader.dto.TradeDTO;
import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.price.PriceListenerImpl;
import com.acme.mytrader.price.PriceSourceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
@AllArgsConstructor
@Getter
public class TradingStrategy {

    private final ExecutionServiceImpl executionService;
    private final PriceSourceImpl priceSource;

    public void automateBuy(List<TradeDTO> tradeRequest) {
        priceSource.addPriceListener(new PriceListenerImpl(executionService));

        tradeRequest.stream()
                .forEach(tr -> priceSource.setPrice(tr.getStock().getStockTicker(), tr.getCurrentPrice()));

        priceSource.removePriceListener(new PriceListenerImpl(executionService));
    }

    public void automateSell(List<TradeDTO> tradeRequest) {
        /*
        TODO
         */
    }

    public List<TradeDTO> getProcessedTrade() {
        return executionService.getTradeProcessed();
    }
}
