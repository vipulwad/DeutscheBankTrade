package com.vipul.DeutscheBankTrade.Store;

import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataStoreRulesTest {
    private TradeInputData source = new TradeInputData();
    private TradeInputData comparison = new TradeInputData();
    DataStoreRules dsr = new DataStoreRules();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void validatorTest1() {
        source.setTradeId("T1");
        source.setVersion(2);
        source.setBookId("B1");
        source.setCounterPartyId("CP-1");
        source.setMaturityDate("20200511");
        source.setCreatedDate("20201220");
        source.setExpired(false);


        comparison.setTradeId("T1");
        comparison.setVersion(1);
        comparison.setBookId("B1");
        comparison.setCounterPartyId("CP-1");
        comparison.setMaturityDate("20200511");
        comparison.setCreatedDate("20201220");
        comparison.setExpired(false);
        assertFalse(dsr.validator(source, comparison));
    }
}