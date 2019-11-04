package org.jtyq.analyzer.observer;

import org.jtyq.analyzer.bean.Stock;
import org.jtyq.analyzer.core.StockListHandler;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class StdoutObserver implements Observer {

    private static final String format1= "股票代码：%s;股票名:%s; 当前价格:%s;涨额: %s;涨幅: %s\n";

    private static final String format2= "%s;%s;%s;%s;%s\n";

    public void update(Observable o, Object arg) {
        StockListHandler stockListHandler = (StockListHandler) o;
        List<Stock> stockList = stockListHandler.getStockList();
        StringBuilder sb = new StringBuilder();
        for(Stock stock:stockList) {
            sb.append(String.format(format2,stock.getCode(),stock.getName(),stock.getCurrentPrice(),stock.getIncrement(),stock.getGain()));
        }
        System.out.println(sb.toString());
    }
}
