package org.jtyq.analyzer.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener implements ApplicationListener<UpdateMessageEvent> {

    @Override
    public void onApplicationEvent(UpdateMessageEvent event) {

    }
}
