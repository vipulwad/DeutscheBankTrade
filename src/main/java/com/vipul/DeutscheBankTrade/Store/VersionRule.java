package com.vipul.DeutscheBankTrade.Store;

import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputData;

public class VersionRule implements DataStoreRule {
    @Override
    public boolean validate(TradeInputData sourceData, TradeInputData comparisonData) {
        if (sourceData == null || comparisonData == null)
            return false;
        if (sourceData.getVersion() > comparisonData.getVersion()) {
            return false;
        }
        return true;
    }
}
