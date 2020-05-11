package com.vipul.DeutscheBankTrade.TradeInputData;

import java.util.ArrayList;
import java.util.List;


public class TradeInputDataRules {

    public boolean validator(TradeInputData data) {
        List<TradeDataRule> rules = new ArrayList<>();
        rules.add(new MaturityDateRule());
        rules.add(new ExpiredRule());

        for (TradeDataRule rule : rules) {
            if (!rule.validate(data))
                return false;
        }

        return true;
    }
}
