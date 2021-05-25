package org.jtyq.analyzer.core;

import org.jtyq.analyzer.bean.Environment;

import java.util.List;
import java.util.Observer;

public class ApplicationContext {

    private Environment environment;

    private Processor processor;


    public ApplicationContext() {
        this.environment = new Environment();
        this.processor = new Processor(environment);
    }

    public void init() {
        try {
            List<String> analyzerClassNames = this.environment.getAnalyzerClassNames();
            for (String s : analyzerClassNames) {
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
        processor.run();
    }
}
