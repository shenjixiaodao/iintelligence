package com.ii.biz.switchgear.common;

import com.ii.biz.switchgear.AyncContinuation.SwitchAyncContinuationService;
import com.ii.biz.switchgear.event.processor.SwitchStatusEventProcessor;
import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceId;
import com.ii.domain.switchgear.event.SwitchStatusChangedEvent;
import com.ii.domain.base.handler.Handler;
import com.ii.domain.switchgear.handler.ISwitchHandlerHolder;
import com.ii.domain.switchgear.handler.SwitchHandler;
import org.springframework.util.Assert;

import java.util.List;
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

    /**
     * todo: 在系统启动的时候，从持久化数据源，初始化{@param handlerMap}， 将已经和设备绑定的加载
     */
    private static ConcurrentMap<DeviceId, BlockingQueue<Handler>> handlerMap = new ConcurrentHashMap<>();

    private static boolean isInit = true;

    public static void init(List<Device> devices){
        Assert.isTrue(isInit, "holder已被初始化不可再执行");
        Assert.notNull(devices, "devices 为空");
        for(Device device:devices){
            //阻塞队列只允许放一个元素
            handlerMap.put(device.deviceId(), new ArrayBlockingQueue<Handler>(1));
        }
        isInit = false;
    }

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
    public Handler getHandler(DeviceId deviceId) {
        BlockingQueue<Handler> queue = handlerMap.get(deviceId);
        if(null == queue) {
            throw new IllegalArgumentException("未绑定设备");
        }
        return queue.peek();
    }

    @Override
    public  Handler putHandler(DeviceId deviceId, Handler handler){
        BlockingQueue<Handler> queue = handlerMap.get(deviceId);
        if(null == queue) {
            throw new IllegalArgumentException("未绑定设备");
        }
        Handler oldHandler = null;
        synchronized (queue) {
            if (!queue.isEmpty()) {
                oldHandler = queue.remove();
            }
            queue.offer(handler);
        }
        return oldHandler;
    }

    @Override
    public  Handler fetchHandler(DeviceId deviceId) {
        return fetchHandler(deviceId, 10, TimeUnit.SECONDS);
    }

    public  Handler fetchHandler(DeviceId deviceId, long time, TimeUnit timeUnit) {
        BlockingQueue<Handler> queue = handlerMap.get(deviceId);
        Handler handler = null;
        try {
            handler = queue.poll(time, timeUnit);
        } catch (InterruptedException e) {
            //e.printStackTrace(); todo 异常处理
        }
        return handler;
    }
}
