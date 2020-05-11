package com.vipul.DeutscheBankTrade;

import com.vipul.DeutscheBankTrade.Store.DataStoreTradeToSqlite;
import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputData;
import com.vipul.DeutscheBankTrade.utils.FormatDate;
import com.vipul.DeutscheBankTrade.utils.ReadInputFile;

import java.util.ArrayList;

public class MainProcessor {
    public static void main(String[] args) {
        ReadInputFile rdfile = new ReadInputFile();
        ArrayList<String[]> rawInputData = (ArrayList<String[]>) (rdfile.readFilesFromFolder("InputData"));
        CSVToTradeInputDataAdaptor tradeAdaptor = new CSVToTradeInputDataAdaptor();
        DataStoreTradeToSqlite outputStore = new DataStoreTradeToSqlite("jdbc:sqlite:SqliteJavaDB.db");
        // DataStoreRules dataStoreRules = new DataStoreRules();
        for (String[] row : rawInputData) {
            TradeInputData tid = tradeAdaptor.convert(row);

            if (tid == null)
                System.out.println("No value returned as validation failed");
            else {
                System.out.println("Expiry in main processor for maturity date : " + tid.getMaturityDate() + " is : " + tid.isExpired());

                System.out.println("Trade Ip data: " + tid.getTradeId() + "|" + tid.getVersion() + "|" + tid.getCounterPartyId() + "|" + tid.getBookId() + "|" + tid.getMaturityDate() + "|" + tid.getCreatedDate() + "|" + tid.isExpired() + ":: EOL");
                TradeInputData fetchedData = outputStore.getMaxVersionForTrade(tid.getTradeId());
                // If no record in DB
                if (fetchedData == null) {
                    outputStore.InsertRow(tid);
                } else if (fetchedData.getVersion() < tid.getVersion()) {
                    // Earlier version is smaller than this trade version
                    outputStore.UpdateAllOldTransactionForTrade(tid.getTradeId(), FormatDate.getTodayDate("yyyyMMdd"));
                    outputStore.InsertRow(tid);
                } else if (fetchedData.getVersion() == tid.getVersion()) {
                    // Earlier version same as this trade version
                    outputStore.UpdateAllOldTransactionForTrade(tid.getTradeId(), FormatDate.getTodayDate("yyyyMMdd"));
                    outputStore.UpdateRow(tid);
                } else
                    System.out.println("No Updates Required to DB");

            }
        }


    }
}
