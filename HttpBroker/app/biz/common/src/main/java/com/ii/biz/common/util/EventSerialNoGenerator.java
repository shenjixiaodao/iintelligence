package com.ii.biz.common.util;


import com.ii.domain.base.EventType;
import com.ii.domain.base.event.AbstractChangeStatusEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对于一次性事件{@link AbstractChangeStatusEvent.PeriodEnabled#No}， 当有些场景需要保证事件的可达性时，
 * 此时需要有明确的流水号来做唯一的记录跟踪：保证事件已经交付，并且收到设备的{@link EventType#OK}响应。
 */
public class EventSerialNoGenerator {
    private static AtomicInteger sequence = new AtomicInteger(100000);

    private static class Timestamp {

        private static ThreadLocal<DateFormat> DATA_FORMAT = new ThreadLocal<DateFormat>() {
            @Override
            public DateFormat get() {
                return super.get();
            }

            @Override
            protected DateFormat initialValue() {
                DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                df.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                df.setLenient(false);
                return df;
            }

            @Override
            public void remove() {
                super.remove();
            }

            @Override
            public void set(DateFormat value) {
                super.set(value);
            }

        };

        private static synchronized String currentTime() {
            return DATA_FORMAT.get().format(System.currentTimeMillis());
        }
    }

    public static String generate(EventType eventType, String serverId) {
        StringBuilder appender = new StringBuilder();
        String timestamp = Timestamp.currentTime();
        int sequence = nextSeq();
        appender.append(eventType).append(serverId).append(timestamp).
                append(sequence);
        return appender.toString();
    }

    private static int nextSeq() {
        for (;;) {
            int current = sequence.get();
            int next = (current > 900000) ? 1000 : current + 1;
            if (sequence.compareAndSet(current, next))
                return next;
        }
    }
}
