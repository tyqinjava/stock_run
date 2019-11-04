//package org.jtyq.analyzer.analyse.impl;
//
//import org.jtyq.analyzer.analyse.AbstractStockAnalyzer;
//import org.jtyq.analyzer.bean.Stock;
//import org.jtyq.analyzer.bean.StockListEvent;
//
//import java.io.*;
//import java.util.List;
//
//public class FileRecordStockAnalyzer extends AbstractStockAnalyzer {
//
//    public static final String filePath = "c:/users/tyq/desktop/warning.txt";
//    private static final String format= "股票代码：%s;股票名:%s; 当前价格:%s;涨额: %s;涨幅: %s\r\n";
//    private static final String dpzs = "SH000001";
//    private static final double dief = 1;
//    private static final double zf = 1;
//
//    public void initStockList(StockListEvent event) {
//
//    }
//
//    public void preStockListUpdate(StockListEvent event) {
//
//    }
//
//    public void afterStockListUpdate(StockListEvent event) {
//        List<Stock> stockList = event.getStockList();
//        Stock stock = findOO1Stock(stockList);
//        if(stock != null) {
//            double gain = Double.parseDouble(stock.getGain());
//            if (gain >= 0 && gain >= zf) {
//                System.out.println("write to file");
//                writeToFile(stockList);
//            } else if (gain < 0) {
//                double absGain = Math.abs(gain);
//                if(absGain >= dief) {
//                    System.out.println("write to file");
//                    writeToFile(stockList);
//                }
//            }
//        }
//    }
//
//    private Stock findOO1Stock(List<Stock> stockList){
//        for (Stock stock : stockList) {
//            if(stock.getCode().equalsIgnoreCase(dpzs)){
//                return stock;
//            }
//        }
//        return null;
//    }
//
//    private void writeToFile(List<Stock> stocks){
//        PrintWriter printWriter;
//        try {
//           printWriter = new PrintWriter(filePath,"UTF-8");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return;
//        }
//        StringBuilder sb = new StringBuilder();
//        for (Stock stock : stocks) {
//            sb.append(String.format(format,stock.getCode(),stock.getName(),stock.getCurrentPrice(),stock.getIncrement(),stock.getGain()));
//        }
//        printWriter.write(sb.toString());
//        printWriter.flush();
//    }
//}
