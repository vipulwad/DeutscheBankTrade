package com.vipul.DeutscheBankTrade.Store;


import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputData;

import java.util.ArrayList;
import java.util.List;

public class DataStoreRules {

    public boolean validator(TradeInputData sourceData, TradeInputData comparisonData){
        List<DataStoreRule> rules = new ArrayList<>();
        rules.add(new VersionRule());

        for (DataStoreRule rule:rules){
            if(!rule.validate(sourceData, comparisonData))
                return  false;
        }

        return true;
    }
}
