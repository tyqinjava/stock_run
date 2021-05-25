package org.jtyq.analyzer.observer;

import org.jtyq.analyzer.bean.Stock;
import org.jtyq.analyzer.core.Handler;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class StdoutObserver implements Observer {

    private static final String format1= "股票代码：%s;股票名:%s; 当前价格:%s;涨额: %s;涨幅: %s\n";

    private static final String format2= "%s;%s;%s;%s;%s\n";

    private static final String format3 = "%s;%s;%s;%s\n";

    public void update(Observable o, Object arg) {
        List<Stock> stockList = (List<Stock>) arg;
        StringBuilder sb = new StringBuilder();
        for(Stock stock:stockList) {
            sb.append(String.format(format3,stock.getName(),stock.getCurrentPrice(),stock.getIncrement(),stock.getGain()));
        }
        System.out.println(sb.toString());
    }
}
