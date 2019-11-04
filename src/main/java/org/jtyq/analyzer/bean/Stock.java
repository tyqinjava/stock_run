package org.jtyq.analyzer.bean;

import java.util.Date;

public class Stock implements Comparable<Stock>{

    private String code;
    private String name;
    private String currentPrice;
    private String gain;
    private long timeStamp;
    private Date resultDate;
    private String increment;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }


    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getGain() {
        return gain;
    }

    public void setGain(String gain) {
        this.gain = gain;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                ", gain='" + gain + '\'' +
                ", timeStamp=" + timeStamp +
                ", resultDate=" + resultDate +
                ", increment='" + increment + '\'' +
                '}';
    }

    @Override
    public int compareTo(Stock o) {
        return name.compareTo(o.name);
    }
}
