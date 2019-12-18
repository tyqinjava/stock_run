package org.jtyq.analyzer.bean;

public class Constants {
    public static final String XQ_BASE_URL = "https://xueqiu.com/v4/stock/quotec.json?code=";
    public static final String XQ_QUERY_URL ="https://stock.xueqiu.com/v5/stock/quote.json?symbol=";

    public static final String SINA_URL ="http://hq.sinajs.cn/list=s_";

    public enum DataSourceType{
        XQ_BASE,XUE_V5,SINA
    }
}
