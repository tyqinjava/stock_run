package org.jtyq.analyzer.observer;

import org.jtyq.analyzer.bean.Stock;
import org.jtyq.analyzer.core.FileLog;
import org.jtyq.analyzer.core.Log;

import java.io.*;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class FileRecordObserver implements Observer {

    public static final String filename = "warning.txt";
    private static final String format= "股票代码：%s;股票名:%s; 当前价格:%s;涨额: %s;涨幅: %s\r\n";
    private static final String dpzs = "SH000001";
    private static final double dief = 1;
    private static final double zf = 1;

    private Log log = new FileLog();

    private Stock findOO1Stock(List<Stock> stockList){
        for (Stock stock : stockList) {
            if(stock .getCode().equalsIgnoreCase(dpzs)){
                return stock;
            }
        }
        return null;
    }

    private void writeToFile(List<Stock> stocks){
        PrintWriter printWriter = null;
        log.info("开始写入信息!");
        try {
            URL url =  getClass().getResource("/");
            log.info("获取到URL："+ url);
           File file =  AccessController.doPrivileged(new PrivilegedAction<File>() {
                @Override
                public File run() {
                    File file = new File(url.getFile()+ "/" + filename);
                    System.out.println(file);
                    return file;
                }
            });
           log.info(file);
            System.out.println(file);
            FileOutputStream fout = new FileOutputStream(file,true);
            printWriter = new PrintWriter(new OutputStreamWriter(fout,"UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for (Stock stock : stocks) {
            sb.append(String.format(format,stock.getCode(),stock.getName(),stock.getCurrentPrice(),stock.getIncrement(),stock.getGain()));
        }
        printWriter.write(sb.toString());
        printWriter.flush();
    }

    public void update(Observable o, Object arg) {
        List<Stock> stockList = (List<Stock>) arg;
        Stock stock = findOO1Stock(stockList);
        if(stock != null) {
            double gain = Double.parseDouble(stock.getGain());
            if (gain >= 0 && gain >= zf) {
                System.out.println("write to file");
                writeToFile(stockList);
            } else if (gain < 0) {
                double absGain = Math.abs(gain);
                if(absGain >= dief) {
                    System.out.println("write to file");
                    writeToFile(stockList);
                }
            }
        }
    }
}
