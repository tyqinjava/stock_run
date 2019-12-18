package org.jtyq.analyzer;


import org.jtyq.analyzer.core.Processor;

import java.io.IOException;
import java.util.Observer;

public class Application {

    public Processor processor;
    public static final String analyzerClassNameKey = "analyzer.class.name";

    public Application() {
        try {
            this.processor = new Processor();
            String analyzerClassName = processor.getPropertiesValue().getConfigValue(analyzerClassNameKey);
            String[] classes = analyzerClassName.split(";");
            for (String s : classes) {
                Class ac = Class.forName(s);
                Observer analyzer = (Observer) ac.newInstance();
                processor.addObserver(analyzer);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void start() {
        processor.run();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new Application().start();
    }


}
