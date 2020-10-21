package com.acme.mytrader.dto;

import com.acme.mytrader.enums.Stock;
import com.acme.mytrader.enums.TradeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
public class TradeDTO {
    private final Stock stock;
    private final double currentPrice;
    private final TradeType tradeType;
    private final int tradeVolume;
}
