package org.jtyq.analyzer.bean;

import java.util.List;

public class StockListEvent {

    private List<Stock> stockList;

    public StockListEvent(List<Stock> stockList){
        this.stockList = stockList;
    }

    public List<Stock> getStockList() {
        return stockList;
    }
}
