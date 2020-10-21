package com.acme.mytrader.execution;

import com.acme.mytrader.dto.TradeDTO;
import com.acme.mytrader.enums.Stock;
import com.acme.mytrader.enums.TradeType;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class ExecutionServiceImpl implements ExecutionService {

    private static Logger LOGGER = Logger.getLogger(ExecutionServiceImpl.class.getName());

    @Getter
    private List<TradeDTO> tradeProcessed = new CopyOnWriteArrayList<>();

    @Override
    public void buy(String security, double price, int volume) {
        LOGGER.info(" >>> Buy trade executed for " + security + " @ £" + price + " for "+ volume + " number of securities.");
        tradeProcessed.add(TradeDTO.builder().stock(Stock.valueOf(security)).tradeType(TradeType.BUY).currentPrice(price).tradeVolume(volume).build());
    }

    @Override
    public void sell(String security, double price, int volume) {
        LOGGER.info(" >>> Out of scope");
        throw new UnsupportedOperationException(" >>> Out of scope");
    }

    public List<TradeDTO> getTrades() {
        return this.tradeProcessed;
    }
}
