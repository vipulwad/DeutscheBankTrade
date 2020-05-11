package com.vipul.DeutscheBankTrade;

import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputData;
import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputDataRules;
import com.vipul.DeutscheBankTrade.utils.FormatDate;

public class CSVToTradeInputDataAdaptor {
    private TradeInputData tradeInputData = new TradeInputData();

    public TradeInputData convert(String[] inputCSV) {
        System.out.println("Converting the String to TradeData: ");
        tradeInputData.setTradeId(inputCSV[0]);
        tradeInputData.setVersion(Integer.parseInt(inputCSV[1]));
        tradeInputData.setCounterPartyId(inputCSV[2]);
        tradeInputData.setBookId(inputCSV[3]);
        tradeInputData.setMaturityDate(FormatDate.convertDateStringFormat(inputCSV[4], "dd/MM/yyyy", "yyyyMMdd"));
        tradeInputData.setCreatedDate(FormatDate.getTodayDate("yyyyMMdd"));
        int comparisonVal = tradeInputData.getMaturityDate().compareTo(FormatDate.getTodayDate("yyyyMMdd"));
        if(comparisonVal < 1)
            tradeInputData.setExpired(true);
        else
            tradeInputData.setExpired(false);


        System.out.println("Converted data: " + tradeInputData.getTradeId() + "|" + tradeInputData.getVersion() + "|" + tradeInputData.getCounterPartyId() + "|" + tradeInputData.getBookId() + "|" + tradeInputData.getMaturityDate() + "|" + tradeInputData.getCreatedDate() + "|" + tradeInputData.isExpired() + ":: END");
        TradeInputDataRules validationRules = new TradeInputDataRules();
        if (validationRules.validator(tradeInputData)) {
            return tradeInputData;
        } else return null;
    }
}
