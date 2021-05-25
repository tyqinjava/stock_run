package org.jtyq.analyzer.task;

import org.jtyq.analyzer.bean.Constants;
import org.jtyq.analyzer.bean.Environment;
import org.jtyq.analyzer.bean.StKey;
import org.jtyq.analyzer.bean.Stock;
import org.jtyq.analyzer.core.SimpleStockParse;
import org.jtyq.analyzer.core.Handler;
import org.jtyq.analyzer.core.SinaStockParse;
import org.jtyq.analyzer.core.StockParse;
import org.jtyq.analyzer.util.HttpRequestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class ProcessTask extends TimerTask {

    private Environment environment;

    private Handler handler;

    public ProcessTask(Environment environment, Handler handler) {
        this.environment = environment;
        this.handler = handler;
    }

    public void run() {
        List<StKey> stKeys = environment.getStKeys();
        List<Stock> stocks = new ArrayList<>();
        stKeys.forEach(v -> {
            String url = getUrl(v.getCode(), Constants.DataSourceType.SINA);
            HttpRequestClient client = new HttpRequestClient();
            String result = client.sendHttpGet(url);
            StockParse jsonParse = new SinaStockParse();
            Stock stock = jsonParse.parse(result);
            stock.setCode(v.getCode());
            stock.setName(v.getName());
            stocks.add(stock);
        });
        handler.setChanged();
        handler.notifyObservers(stocks);
    }

    private String getUrl(String code, Constants.DataSourceType type) {
        String url = null;
        switch (type) {
            case XQ_BASE:
                url = Constants.XQ_BASE_URL + code;
                break;
            case SINA:
                url = Constants.SINA_URL + code.toLowerCase();
                break;
            case XUE_V5:
                url = Constants.XQ_QUERY_URL + code;
                break;
            default:
        }
        return url;
    }
}
