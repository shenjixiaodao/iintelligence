package com.ii.biz.common;

import com.ii.biz.AyncContinuation.SwitchAyncContinuationService;
import com.ii.biz.event.processor.SwitchStateEventProcessor;
import com.ii.domain.base.DeviceId;
import com.ii.domain.event.ChangeSwitchStateOKEvent;
import com.ii.domain.handler.IUserSwitchHandlerHolder;
import com.ii.domain.handler.UserSwitchHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * 全局共享的 handler 资源对象，该holder持有的handler{@link UserSwitchHandler}集合都是等待设备
 * 发起的event{@link ChangeSwitchStateOKEvent}。
 * 1，如果该handler已处理event{@link ChangeSwitchStateOKEvent},则从队里中移除该handler；
 * 2，所有设备请求过来的handler都会按策略加入到该holder集合中{@link SwitchAyncContinuationService}；
 * 3，当收到用户发起的event{@link ChangeSwitchStateOKEvent}，
 * 事件订阅者subscribe{@link SwitchStateEventProcessor}将会从该holder中获取对应的handler来处理对应事件。
 */
public class UserSwitchHandlerHolder implements IUserSwitchHandlerHolder {

    private static ConcurrentMap<DeviceId, UserSwitchHandler> handlerMap = new ConcurrentHashMap<>();

    @Override
    public UserSwitchHandler putHandler(DeviceId deviceId, UserSwitchHandler handler) {
        return handlerMap.put(deviceId, handler);
    }

    @Override
    public UserSwitchHandler fetchHandler(DeviceId deviceId) {
        return handlerMap.remove(deviceId);
    }

    private static class SingletonHolder{
        static UserSwitchHandlerHolder holder = new UserSwitchHandlerHolder();
    }

    private UserSwitchHandlerHolder(){
        //静止被外部实例化
    }

    public static UserSwitchHandlerHolder getHolder(){
        return SingletonHolder.holder;
    }


}
