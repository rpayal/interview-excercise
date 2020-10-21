package com.acme.mytrader.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum Stock {
    GOOGL("GOOGL", 55.0, 75,150.0, 75),
    APPL("APPL", 40.0, 65,200.0, 65),
    IBM("IBM", 55.0, 100,180.0, 75);

    private final String stockTicker;
    private final double buyPriceThreshold;
    private final int buyVolume;
    private final double sellPriceThreshold;
    private final int sellVolume;
    /**
     * @return Random stock
     */
    public static Stock getRandomStock() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
