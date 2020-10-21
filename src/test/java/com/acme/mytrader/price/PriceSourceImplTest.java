package com.acme.mytrader.price;

import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceSourceImplTest {

    @Test
    public void addListener_shouldAddToList() {
        PriceListener priceListener = Mockito.mock(PriceListener.class);
        PriceSourceImpl priceSource = new PriceSourceImpl();
        priceSource.addPriceListener(priceListener);
        assertThat(priceSource.getPriceListeners()).hasSize(1);
    }

    @Test
    public void addTwoAndThenRemoveOnePriceListener_shouldAddAndRemoveListener() {
        PriceListener priceListener1 = Mockito.mock(PriceListener.class);
        PriceListener priceListener2 = Mockito.mock(PriceListener.class);
        PriceSourceImpl priceSource = new PriceSourceImpl();
        priceSource.addPriceListener(priceListener1);
        priceSource.addPriceListener(priceListener2);
        priceSource.removePriceListener(priceListener2);
        assertThat(priceSource.getPriceListeners()).hasSize(1);
    }
}