package org.jtyq.analyzer.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class Environment {

    private Properties stockProperties = new Properties();
    private Properties configProperties = new Properties();

    private static final String STOCK_PROPERTIES = "st.properties";
    private static final String CONFIG_PROPERTIES = "config.properties";
    private static final String TIME_DELAY = "time.delay";
    private static final String TIME_PERIOD = "time.period";

    public static final String ANALYZER_CLASS_NAME_KEY = "analyzer.class.name";


    private List<StKey> stKeys;

    public Environment() {
        String stPath = System.getProperty("stpath");
        String confPath = System.getProperty("confPath");
        InputStream stStream = null;
        InputStream configStream = null;
        if (stPath != null) {
            try {
                stStream = new FileInputStream(stPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            stStream = Environment.class.getClassLoader().getResourceAsStream(STOCK_PROPERTIES);
        }

        if (confPath != null) {
            try {
                configStream = new FileInputStream(confPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            configStream = Environment.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);
        }
        try {
            stockProperties.load(stStream);
            configProperties.load(configStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<StKey> sks = new ArrayList();
        Enumeration<?> enumeration = stockProperties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            StKey stKey = new StKey();
            stKey.setCode(key);
            stKey.setName(stockProperties.getProperty(key));
            sks.add(stKey);
        }
        this.stKeys = Collections.unmodifiableList(sks);
    }

    public List<StKey> getStKeys() {
        return this.stKeys;
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

    public List<String> getAnalyzerClassNames() {
        String acn = this.getConfigValue(ANALYZER_CLASS_NAME_KEY);
        if (acn != null) {
            String[] classes = acn.split(";");
            return Arrays.asList(classes);
        }
        return new ArrayList<>();
    }

}
