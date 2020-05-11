package com.vipul.DeutscheBankTrade.Store;

import com.vipul.DeutscheBankTrade.TradeInputData.TradeInputData;

public abstract class DataStore {
    String connection;
    private DataStore (){}
      DataStore(String connection){
        this.connection = connection;
    }}

