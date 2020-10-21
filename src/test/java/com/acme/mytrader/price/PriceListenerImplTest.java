package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PriceListenerImplTest {

    @Test
    public void givenForMonitoredStock_successBuyTrade() {
        ExecutionService executionService = Mockito.mock(ExecutionService.class);
        ArgumentCaptor<String> acString = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> acDouble = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Integer> acInteger = ArgumentCaptor.forClass(Integer.class);

        PriceListenerImpl priceListener = new PriceListenerImpl(executionService);
        priceListener.priceUpdate("IBM", 25.00);

        verify(executionService, times(1))
                .buy(acString.capture(), acDouble.capture(), acInteger.capture());
    }

    @Test
    public void givenForMonitoredStock_failedBuyTrade() {
        ExecutionService executionService = Mockito.mock(ExecutionService.class);
        ArgumentCaptor<String> acString = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> acDouble = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Integer> acInteger = ArgumentCaptor.forClass(Integer.class);

        PriceListenerImpl priceListener = new PriceListenerImpl(executionService);
        priceListener.priceUpdate("IBM", 100.00);

        verify(executionService, times(0))
                .buy(acString.capture(), acDouble.capture(), acInteger.capture());
    }

    @Test
    public void givenForNotMonitoredStock_missedTrade() {
        ExecutionService executionService = Mockito.mock(ExecutionService.class);
        ArgumentCaptor<String> acString = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> acDouble = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<Integer> acInteger = ArgumentCaptor.forClass(Integer.class);

        PriceListenerImpl priceListener = new PriceListenerImpl(executionService);
        priceListener.priceUpdate("APPL", 35.00);

        verify(executionService, times(0))
                .buy(acString.capture(), acDouble.capture(), acInteger.capture());
    }
}