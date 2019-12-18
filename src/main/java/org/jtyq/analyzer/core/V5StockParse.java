package org.jtyq.analyzer.core;

import org.jtyq.analyzer.bean.Stock;

public class V5StockParse implements StockParse {

    private Stock stock;

    public V5StockParse(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void parse(String json) {
        System.out.println(json);

    }
}
