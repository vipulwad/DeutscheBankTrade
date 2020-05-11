package com.vipul.DeutscheBankTrade;

import com.vipul.DeutscheBankTrade.CSVToTradeInputDataAdaptorTest;
import com.vipul.DeutscheBankTrade.Store.DataStoreRulesTest;
import com.vipul.DeutscheBankTrade.Store.VersionRuleTest;
import com.vipul.DeutscheBankTrade.TradeInputData.MaturityDateRule;
import com.vipul.DeutscheBankTrade.TradeInputData.MaturityDateRuleTest;
import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputDataTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                CSVToTradeInputDataAdaptorTest.class,
                MaturityDateRuleTest.class,
                TradeInputDataTest.class

        }
)
public class SuiteTest {

}
