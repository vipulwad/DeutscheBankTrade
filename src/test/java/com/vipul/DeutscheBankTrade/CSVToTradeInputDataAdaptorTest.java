package com.vipul.DeutscheBankTrade;

import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputData;
import com.vipul.DeutscheBankTrade.utils.ReadInputFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class CSVToTradeInputDataAdaptorTest {

    @Test(expected = NullPointerException.class)
    public void convert1() {
        ReadInputFile readfile = new ReadInputFile();

        ArrayList<String[]> rawInputData = (ArrayList<String[]>) (readfile.readFilesFromFolder("Anyfile"));
        CSVToTradeInputDataAdaptor tradeAdaptor = new CSVToTradeInputDataAdaptor();
        for (String[] row : rawInputData) {
            assertNull(tradeAdaptor.convert(row));
        }
    }

    @Test()
    public void convert2() {
        ReadInputFile readfile = new ReadInputFile();
        ArrayList<String[]> rawInputData = (ArrayList<String[]>) (readfile.readFilesFromFolder("InputData"));
        CSVToTradeInputDataAdaptor tradeAdaptor = new CSVToTradeInputDataAdaptor();
        assertNotNull(tradeAdaptor.convert(rawInputData.get(0)));
    }
}