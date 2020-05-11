package com.vipul.DeutscheBankTrade.TradeInputData;

import com.vipul.DeutscheBankTrade.utils.FormatDate;

public class MaturityDateRule implements TradeDataRule {

    @Override
    public boolean validate(TradeInputData inputData) {
        try {
            int comparisonVal = inputData.getMaturityDate().compareTo(FormatDate.getTodayDate("yyyyMMdd"));
            if (comparisonVal < 0) {
                System.out.println("Maturity date is earlier than today");
                return false;
            }
        } catch (NullPointerException e) {
            System.out.println("Comparison between dates failed " + e);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Exception Raised " + e);
            return false;
        }

        return true;
    }
}
