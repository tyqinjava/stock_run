package org.jtyq.analyzer;


import org.jtyq.analyzer.core.ApplicationContext;

public class Main {

    public static void main(String[] args) {
           new Thread(() -> {
               ApplicationContext context = new ApplicationContext();
               context.init();
           }).start();
    }


}
