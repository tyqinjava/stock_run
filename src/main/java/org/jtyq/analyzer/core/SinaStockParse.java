package org.jtyq.analyzer.core;

import org.jtyq.analyzer.bean.Stock;

import java.util.Date;

public class SinaStockParse implements StockParse {

    private Stock stock;

    public SinaStockParse(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void parse(String json) {
        String sl = json.substring(json.indexOf("\"")+1,json.lastIndexOf("\""));
        String[] its = sl.split(",");

        String name = its[0];
        String price= its[1];
        String gain = its[2];
        String increment = its[3];

        Long timestamp = System.currentTimeMillis();
        this.stock.setCurrentPrice(price);
        this.stock.setIncrement(increment);
        this.stock.setResultDate(new Date());
        this.stock.setTimeStamp(timestamp);
        this.stock.setGain(gain);
    }
}
