package com.vipul.DeutscheBankTrade.Store;

import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputData;

public interface DataStoreRule {
    boolean validate(TradeInputData sourceData, TradeInputData comparisonData);
}
