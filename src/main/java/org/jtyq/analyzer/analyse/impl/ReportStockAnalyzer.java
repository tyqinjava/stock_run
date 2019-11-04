//package org.jtyq.analyzer.analyse.impl;
//
//import org.jtyq.analyzer.analyse.AbstractStockAnalyzer;
//import org.jtyq.analyzer.bean.Stock;
//import org.jtyq.analyzer.bean.StockListEvent;
//import org.jtyq.analyzer.core.StockListHandler;
//
//import java.util.List;
//
//public class ReportStockAnalyzer extends AbstractStockAnalyzer {
//
//    private static final String format1= "股票代码：%s;股票名:%s; 当前价格:%s;涨额: %s;涨幅: %s\n";
//    private static final String format2= "%s;%s;%s;%s;%s\n";
//
//    public ReportStockAnalyzer() {
//    }
//
//    @Override
//    public void analyze() {
//        super.analyze();
//    }
//
//    public void initStockList(StockListEvent event) {
//        //System.out.println("stockList is init！");
//    }
//
//    public void preStockListUpdate(StockListEvent event) {
//        //System.out.println("before stockListUpdate !");
//    }
//    public void afterStockListUpdate(StockListEvent event) {
//        StockListHandler stockListHandler = getStockListHandler();
//        List<Stock> stockList = stockListHandler.getStockList();
//        StringBuilder sb = new StringBuilder();
//        for(Stock stock:stockList) {
//            sb.append(String.format(format2,stock.getCode(),stock.getName(),stock.getCurrentPrice(),stock.getIncrement(),stock.getGain()));
//        }
//        System.out.println(sb.toString());
//    }
//}
