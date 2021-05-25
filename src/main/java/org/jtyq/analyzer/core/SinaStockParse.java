package org.jtyq.analyzer.core;

import org.jtyq.analyzer.bean.Stock;

import java.util.Date;

public class SinaStockParse implements StockParse {

    @Override
    public Stock parse(String json) {
        String sl = json.substring(json.indexOf("\"")+1,json.lastIndexOf("\""));
        String[] its = sl.split(",");

        String name = its[0];
        String price= its[1];
        String gain = its[2];
        String increment = its[3];

        Long timestamp = System.currentTimeMillis();
        Stock stock = new Stock();
        stock.setCurrentPrice(price);
        stock.setIncrement(increment);
        stock.setResultDate(new Date());
        stock.setTimeStamp(timestamp);
        stock.setGain(gain);
        return stock;
    }
}
