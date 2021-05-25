package org.jtyq.analyzer.core;

import org.jtyq.analyzer.bean.Stock;

public class V5StockParse implements StockParse {

    public V5StockParse() {
    }

    @Override
    public Stock parse(String json) {
        System.out.println(json);
        return null;
    }
}
