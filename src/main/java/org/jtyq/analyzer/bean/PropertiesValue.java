package org.jtyq.analyzer.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;


public class PropertiesValue {

    private static final Properties stockProperties = new Properties();
    private static final Properties configProperties = new Properties();

    private static final String STOCK_PROPERTIES = "st.properties";
    private static final String CONFIG_PROPERTIES = "config.properties";
    private static final String TIME_DELAY = "time.delay";
    private static final String TIME_PERIOD = "time.period";

    public PropertiesValue() {
    }

    static {
        String stPath = System.getProperty("stpath");
        String confPath = System.getProperty("confPath");
        InputStream stream1 = null;
        InputStream stream2 = null;
        if(stPath != null) {
            try {
                stream1 = new FileInputStream(stPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            stream1=PropertiesValue.class.getClassLoader().getResourceAsStream(STOCK_PROPERTIES);
        }

        if(confPath != null) {
            try {
                stream2 = new FileInputStream(confPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            stream2=PropertiesValue.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);
        }
        try {
            stockProperties.load(stream1);
            configProperties.load(stream2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStockName(String code) {
        return stockProperties.getProperty(code);
    }

    public Enumeration getStockKeys() {
        return stockProperties.propertyNames();
    }

    public long getTimeDelay() {
        String timeDelay = this.configProperties.getProperty(TIME_DELAY);
        return Long.parseLong(timeDelay);
    }

    public long getTimePeriod() {
        String timePeriod = this.configProperties.getProperty(TIME_PERIOD);
        return Long.parseLong(timePeriod);
    }

    public String getConfigValue(String configKey) {
        return this.configProperties.getProperty(configKey);
    }

}
