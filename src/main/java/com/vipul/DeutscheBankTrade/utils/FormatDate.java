package com.vipul.DeutscheBankTrade.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {

    public static String getTodayDate(String format) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return (formatter.format(date));
    }

    public static String convertDateStringFormat(String sourceDate, String sourceFormat, String destinationFormat)  {
        try {
            Date date = new SimpleDateFormat(sourceFormat).parse(sourceDate);
            DateFormat dateFormat = new SimpleDateFormat(destinationFormat);
            return  dateFormat.format(date).toString();
        }catch (ParseException e){
            System.out.println("Error in Parsing date " + sourceDate + " in format %s" + sourceFormat);
            return null;
        }

    }
}

