package com.acme.mytrader.strategy;

import com.acme.mytrader.dto.TradeDTO;
import com.acme.mytrader.enums.Stock;
import com.acme.mytrader.execution.ExecutionServiceImpl;
import com.acme.mytrader.price.PriceSourceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TradingStrategyIntegrationTest {

    TradingStrategy tradingStrategy;
    ExecutionServiceImpl executionService;
    PriceSourceImpl priceSource;

    @Before
    public void setup() {
        executionService = new ExecutionServiceImpl();
        priceSource = new PriceSourceImpl();
        tradingStrategy = new TradingStrategy(executionService, priceSource);
    }

    @Test
    public void givenTradeStrategyToBuy_Success() {
        List<TradeDTO> tradeRequest = createTradeRequest();
        long totalSuccessfulIBMTrade = tradeRequest
                .stream()
                .filter(tr -> tr.getStock().equals(Stock.IBM)
                        && tr.getCurrentPrice() <= Stock.IBM.getBuyPriceThreshold())
                .count();
        tradingStrategy.automateBuy(tradeRequest);

        assertEquals(totalSuccessfulIBMTrade, tradingStrategy.getExecutionService().getTradeProcessed().size());
    }

    private List<TradeDTO> createTradeRequest() {
        List<TradeDTO> tradeRequest = new ArrayList<>();
        final double RANGE_MIN = 1.00;
        final double RANGE_MAX = 200.00;
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            Stock stock = Stock.getRandomStock();
            double price = RANGE_MIN + (RANGE_MAX - RANGE_MIN) * rand.nextDouble();
            tradeRequest.add(TradeDTO.builder().stock(stock).currentPrice(price).tradeVolume(100).build());
        }
        return tradeRequest;
    }

}