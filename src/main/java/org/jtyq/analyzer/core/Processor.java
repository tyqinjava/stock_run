package org.jtyq.analyzer.core;

import org.jtyq.analyzer.bean.PropertiesValue;
import org.jtyq.analyzer.task.ProcessTask;

import java.util.Observer;
import java.util.Timer;

public class Processor extends Timer {

    private StockListHandler stockListHandler;
    private PropertiesValue propertiesValue;

    public Processor() {
        this.propertiesValue = new PropertiesValue();
        this.stockListHandler = new StockListHandler(propertiesValue);
    }

    public StockListHandler getStockListHandler() {
        return stockListHandler;
    }


    public void setStockListHandler(StockListHandler stockListHandler) {
        this.stockListHandler = stockListHandler;
    }


    public void setPropertiesValue(PropertiesValue propertiesValue) {
        this.propertiesValue = propertiesValue;
    }


    public PropertiesValue getPropertiesValue() {
        return propertiesValue;
    }

    public void run() {
        schedule(new ProcessTask(stockListHandler)
                , propertiesValue.getTimeDelay(), propertiesValue.getTimePeriod());
//        if(stockAnalyzer == null){
//            throw new RuntimeException("stockAnalyzer is null");
//        }
//        stockAnalyzer.setStockListHandler(stockListHandler);
//        stockAnalyzer.analyze();
    }

    public void addObserver(Observer analyzer) {
        this.stockListHandler.addObserver(analyzer);
    }
}
