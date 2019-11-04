package org.jtyq.analyzer.task;

import org.jtyq.analyzer.bean.Constants;
import org.jtyq.analyzer.bean.Stock;
import org.jtyq.analyzer.core.StockParse;
import org.jtyq.analyzer.util.HttpRequestClient;

public class Executor {

    private Stock stock;
    private HttpRequestClient httpRequestClient;

    public Executor(Stock stock, HttpRequestClient httpRequestClient) {
        this.stock = stock;
        this.httpRequestClient = httpRequestClient;
    }
    public void execute() {
        String url =  Constants.BASE_URL+ stock.getCode();
        String result = httpRequestClient.sendHttpGet(url, null, null);
        StockParse jsonParse = new StockParse(stock);
        jsonParse.parse(result);

    }
}
