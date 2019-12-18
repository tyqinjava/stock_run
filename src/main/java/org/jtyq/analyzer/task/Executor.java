package org.jtyq.analyzer.task;

import org.jtyq.analyzer.bean.Constants;
import org.jtyq.analyzer.bean.Stock;
import org.jtyq.analyzer.core.SimpleStockParse;
import org.jtyq.analyzer.core.SinaStockParse;
import org.jtyq.analyzer.core.StockParse;
import org.jtyq.analyzer.core.V5StockParse;
import org.jtyq.analyzer.util.HttpRequestClient;

public class Executor {

    private Stock stock;
    private HttpRequestClient httpRequestClient;

    public Executor(Stock stock, HttpRequestClient httpRequestClient) {
        this.stock = stock;
        this.httpRequestClient = httpRequestClient;
    }
    public void execute() {
        String url = getUrl(stock.getCode(),Constants.DataSourceType.XQ_BASE);
        String result = httpRequestClient.sendHttpGet(url, null, null);
        StockParse jsonParse = new SimpleStockParse(stock);
        jsonParse.parse(result);
    }

    private String getUrl(String code,Constants.DataSourceType type) {
        String url = null;
        switch (type) {
            case XQ_BASE:
                url = Constants.XQ_BASE_URL+ stock.getCode();
                break;
            case SINA:
                url = Constants.SINA_URL + stock.getCode().toLowerCase();
                break;
            case XUE_V5:
                url = Constants.XQ_QUERY_URL + stock.getCode();
                break;
            default:

        }
        return url;
    }
}
