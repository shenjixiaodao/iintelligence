package com.ii.biz.common.common.event.eventbus;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by liyou on 2017/5/13.
 */
public class ScheduledEventBus {

    private final EventBus eventBus;

    private final ScheduledExecutorService executorService;

    public ScheduledEventBus(EventBus eventBus){
        this.eventBus = eventBus;
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void post(Object event){
        eventBus.post(event);
    }

    /**
     * 延迟固定秒数发布事件
     * @param event
     * @param delay
     */
    public void post(final Object event, int delay){
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                eventBus.post(event);
            }
        }, delay, TimeUnit.SECONDS);
    }
}
