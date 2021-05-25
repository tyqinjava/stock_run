package org.jtyq.analyzer.core;

import org.jtyq.analyzer.bean.Environment;
import org.jtyq.analyzer.task.ProcessTask;

import java.util.Observer;
import java.util.Timer;

public class Processor {

    private Handler handler;

    private Environment environment;

    private Timer timer;

    public Processor(Environment environment) {
        this.environment = environment;
        this.handler = new Handler();
        this.timer = new Timer();
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void run() {
        this.timer.schedule(new ProcessTask(environment, handler), environment.getTimeDelay(), environment.getTimePeriod());
    }

    public void addObserver(Observer analyzer) {
        this.handler.addObserver(analyzer);
    }
}
