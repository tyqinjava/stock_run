package org.jtyq.analyzer.bean;

import org.jtyq.analyzer.core.SinaStockParse;
import org.jtyq.analyzer.core.StockParse;

public enum DataSourceType {

    SINA("http://hq.sinajs.cn/list=s_", new SinaStockParse());

    private String url;

    private StockParse parse;

    DataSourceType(String url, StockParse parse) {
        this.url = url;
        this.parse = parse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public StockParse getParse() {
        return parse;
    }

    public void setParse(StockParse parse) {
        this.parse = parse;
    }

    public String getRequestUrl(String code) {
        return null;
    }

    public boolean checkData(String result) {
        return false;
    }
}
