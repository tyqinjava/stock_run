package org.jtyq.analyzer.core;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLog implements Log {

    private static final String logfile = "C:\\Users\\TYQ\\Desktop\\log.txt";

    private static final String INFO_FORMAT = "【级别】：信息，【日期】：%s,【内容】：%s ";

    private static final String ERROR_FORMAT= "【级别】：错误，【日期】：%s,【内容】：%s ";

    private static final String DATE_FORMAT ="yyyy-MM-dd HH:mm:ss:SSS";

    private PrintWriter printWriter;

    public FileLog(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(logfile,true);
            this.printWriter = new PrintWriter(new OutputStreamWriter(fileOutputStream,"UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void info(Object msg) {
        printWriter.println(String.format(INFO_FORMAT,getDate(),msg));
        printWriter.flush();
    }

    @Override
    public void error(Object error) {
        printWriter.println(String.format(ERROR_FORMAT,getDate(),error));
        printWriter.flush();
    }

    private String getDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

}
