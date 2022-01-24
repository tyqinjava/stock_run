package org.jtyq.analyzer.service;

import org.jtyq.analyzer.bean.*;
import org.jtyq.analyzer.core.SinaStockParse;
import org.jtyq.analyzer.core.StockParse;
import org.jtyq.analyzer.listener.UpdateMessageEvent;
import org.jtyq.analyzer.util.HttpRequestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateMessageService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateMessageService.class);

    @Autowired
    AppConfig config;

    @Autowired
    ApplicationContext context;

    private HttpRequestClient client = new HttpRequestClient();


    public void doUpdate() {
        List<StKey> stKeys = config.getStKeys();
        List<Stock> stocks = new ArrayList<>();
        stKeys.forEach(v -> {
            DataSourceType source = config.getDataSourceType();
            String url = source.getRequestUrl(v.getCode());
            String result = client.sendHttpGet(url);
            if (!source.checkData(result)) {
                logger.error("datasource invalid!");
                return;
            }
            StockParse jsonParse = source.getParse();
            Stock stock = jsonParse.parse(result);
            stock.setCode(v.getCode());
            stock.setName(v.getName());
            stocks.add(stock);
        });
        UpdateMessageEvent event = new UpdateMessageEvent(this);
        event.setStockList(stocks);
        context.publishEvent(event);
    }
}
