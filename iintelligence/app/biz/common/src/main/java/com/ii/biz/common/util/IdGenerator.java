package com.ii.biz.common.util;

import com.ii.domain.base.DeviceType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static AtomicInteger sequence = new AtomicInteger(1000);

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

    public static String generate(DeviceType deviceType, String partitionId) {
        if (partitionId.length() != 2) {
            throw new IllegalArgumentException("分库分表位长度必须为2");
        }
        StringBuilder appender = new StringBuilder();
        String timestamp = Timestamp.currentTime();
        int sequence = nextSeq();
        appender.append(deviceType).append(timestamp).append(sequence)
            .append(partitionId);
        return appender.toString();
    }

    private static int nextSeq() {
        for (;;) {
            int current = sequence.get();
            int next = (current > 9000) ? 1000 : current + 1;
            if (sequence.compareAndSet(current, next))
                return next;
        }
    }
}
