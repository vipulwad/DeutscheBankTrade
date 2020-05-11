package com.vipul.DeutscheBankTrade.TradeInputData;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MaturityDateRuleTest {
    private TradeInputData data = new TradeInputData();
    private MaturityDateRule mdr = new MaturityDateRule();

    @Test
    public void validate1() {

        data.setTradeId("T1");
        data.setVersion(2);
        data.setBookId("B1");
        data.setCounterPartyId("CP-1");
        data.setMaturityDate("20100511");
        data.setCreatedDate("20201220");
        data.setExpired(false);

        assertFalse(mdr.validate(data));
    }

    @Test
    public void validate2() {

        data.setTradeId("T1");
        data.setVersion(2);
        data.setBookId("B1");
        data.setCounterPartyId("CP-1");
        data.setMaturityDate("20300511");
        data.setCreatedDate("20201220");
        data.setExpired(false);

        assertTrue(mdr.validate(data));
    }
}