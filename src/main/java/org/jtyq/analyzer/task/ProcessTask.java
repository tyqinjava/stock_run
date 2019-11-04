package org.jtyq.analyzer.task;

import org.jtyq.analyzer.bean.Stock;
import org.jtyq.analyzer.core.StockListHandler;
import org.jtyq.analyzer.util.HttpRequestClient;

import java.util.List;
import java.util.TimerTask;

public class ProcessTask extends TimerTask {

    private StockListHandler stockListHandler;
    private HttpRequestClient httpRequestClient;


    public ProcessTask(StockListHandler stockListHandler) {
        this.stockListHandler = stockListHandler;
        this.httpRequestClient = new HttpRequestClient();
    }

    public void run() {
//        this.stockListHandler.setStockListStatus(StockListStatus.PRE_UPDATE);
        List<Stock> stockList = stockListHandler.getStockList();
        for (Stock stock : stockList) {
            new Executor(stock, httpRequestClient).execute();
        }
        stockListHandler.setChanged();
        stockListHandler.notifyObservers();
//        this.stockListHandler.setStockListStatus(StockListStatus.AFTER_UPDATE);
    }
}
