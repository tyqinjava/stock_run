package org.jtyq.analyzer.bean;

public interface StockListListener {

    void initStockList(StockListEvent event);

    void preStockListUpdate(StockListEvent event);

    void afterStockListUpdate(StockListEvent event);

}
