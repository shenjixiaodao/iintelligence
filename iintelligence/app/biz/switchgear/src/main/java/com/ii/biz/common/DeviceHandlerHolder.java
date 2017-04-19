package com.ii.biz.common;

import com.ii.domain.base.DeviceId;
import com.ii.domain.continuation.SwitchStateHandler;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 全局共享的 handler 资源对象，该holder持有的handler{@link SwitchStateHandler}集合都是等待用户的
 * 发起的event{@link com.ii.domain.event.SwitchStateChangedEvent}。
 * 1，如果该handler已处理event{@link com.ii.domain.event.SwitchStateChangedEvent},则从队里中移除该handler；
 * 2，所有设备请求过来的handler都会按策略加入到该holder集合中{@link com.ii.biz.AyncContinuation.AyncContinuationService}；
 * 3，当收到用户发起的event{@link com.ii.domain.event.SwitchStateChangedEvent}，
 * 事件订阅者subscribe{@link com.ii.biz.event.processor.SwitchStateEventProcessor}将会从该holder中获取对应的handler来处理对应事件。
 */
public class DeviceHandlerHolder {

    private static ConcurrentMap<DeviceId, SwitchStateHandler> handlerMap = new ConcurrentHashMap<>();

    public static SwitchStateHandler getHandler(DeviceId deviceId){
        return handlerMap.get(deviceId);
    }

    public static SwitchStateHandler putHandler(DeviceId deviceId, SwitchStateHandler handler){
        return handlerMap.put(deviceId, handler);
    }

    public static SwitchStateHandler removeHandler(DeviceId deviceId){
        return handlerMap.remove(deviceId);
    }
}
