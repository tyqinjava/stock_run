package org.jtyq.analyzer.listener;

import org.jtyq.analyzer.bean.Stock;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class UpdateMessageEvent extends ApplicationEvent {

    private List<Stock> stockList;

    public UpdateMessageEvent(Object source) {
        super(source);
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(List<Stock> stockList) {
        this.stockList = stockList;
    }
}
