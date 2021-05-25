package org.jtyq.analyzer.core;

import com.alibaba.fastjson.JSON;
import org.jtyq.analyzer.bean.Stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SimpleStockParse implements StockParse {

    public Stock parse(String json) {
        Map<String, Object> rs = (Map<String, Object>) JSON.parse(json);
        Set<String> s = rs.keySet();
        String ks = null;
        for (String key : s) {
            ks = key;
        }
        List<String> ls = (List<String>) rs.get(ks);
        String price = ls.get(0);
        String increment = ls.get(1);
        String gain = ls.get(2);
        String date = ls.get(3);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);//MMM dd hh:mm:ss Z yyyy
        Date resultDate = null;
        try {
            resultDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long timestamp = System.currentTimeMillis();
        Stock stock = new Stock();
        stock.setCurrentPrice(price);
        stock.setIncrement(increment);
        stock.setResultDate(resultDate);
        stock.setTimeStamp(timestamp);
        stock.setGain(gain);
        return stock;
    }
}
