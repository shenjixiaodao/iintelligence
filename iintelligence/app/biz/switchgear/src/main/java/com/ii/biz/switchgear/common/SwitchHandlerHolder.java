package com.ii.biz.switchgear.common;

import com.ii.biz.switchgear.AyncContinuation.SwitchAyncContinuationService;
import com.ii.biz.switchgear.event.processor.SwitchStatusEventProcessor;
import com.ii.domain.base.DeviceId;
import com.ii.domain.event.SwitchStatusChangedEvent;
import com.ii.domain.handler.ISwitchHandlerHolder;
import com.ii.domain.handler.SwitchHandler;

import java.util.concurrent.*;

/**
 * 全局共享的 handler 资源对象，该holder持有的handler{@link SwitchHandler}集合都是等待用户的
 * 发起的event{@link SwitchStatusChangedEvent}。
 * 1，如果该handler已处理event{@link SwitchStatusChangedEvent},则从队里中移除该handler；
 * 2，所有设备请求过来的handler都会按策略加入到该holder集合中{@link SwitchAyncContinuationService}；
 * 3，当收到用户发起的event{@link SwitchStatusChangedEvent}，
 * 事件订阅者subscribe{@link SwitchStatusEventProcessor}将会从该holder中获取对应的handler来处理对应事件。
 */
public class SwitchHandlerHolder implements ISwitchHandlerHolder{

    private static ConcurrentMap<DeviceId, BlockingQueue<SwitchHandler>> handlerMap = new ConcurrentHashMap<>();

    private static class SingletonHolder{
        static SwitchHandlerHolder holder = new SwitchHandlerHolder();
    }

    private SwitchHandlerHolder(){
        //静止被外部实例化
    }

    public static SwitchHandlerHolder getHolder(){
        return SingletonHolder.holder;
    }

    @Override
    public  SwitchHandler putHandler(DeviceId deviceId, SwitchHandler handler){
        BlockingQueue<SwitchHandler> queue = handlerMap.get(deviceId);
        if(null == queue) {
            queue = new ArrayBlockingQueue<>(1);
            handlerMap.putIfAbsent(deviceId,queue);
            queue = handlerMap.get(deviceId);//保证一定拿到同一个BlockingQueue实例
        }
        SwitchHandler oldHandler = null;
        synchronized (queue) {
            if (!queue.isEmpty()) {
                oldHandler = queue.remove();
            }
            queue.offer(handler);
        }
        return oldHandler;
    }

    @Override
    public  SwitchHandler fetchHandler(DeviceId deviceId) {
        return fetchHandler(deviceId, 10, TimeUnit.SECONDS);
    }

    public  SwitchHandler fetchHandler(DeviceId deviceId, long time, TimeUnit timeUnit) {
        BlockingQueue<SwitchHandler> queue = handlerMap.get(deviceId);
        SwitchHandler handler = null;
        try {
            handler = queue.poll(time, timeUnit);
        } catch (InterruptedException e) {
            //e.printStackTrace(); todo 异常处理
        }
        return handler;
    }
}
