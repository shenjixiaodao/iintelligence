package com.ii.biz.common.event.processor;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeadEventProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(DeadEventProcessor.class);

    @Subscribe
    public void processDeadEvent(DeadEvent deadEvent) {
        LOGGER.info("DEADEVENT DETECTED:{}", deadEvent.getEvent().getClass());

    }
}