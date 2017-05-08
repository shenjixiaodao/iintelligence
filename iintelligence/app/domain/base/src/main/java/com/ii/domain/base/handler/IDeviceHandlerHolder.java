package com.ii.domain.base.handler;

import com.ii.domain.base.DeviceId;
import com.ii.domain.base.event.StatusChangedEvent;
import com.ii.domain.base.service.DeviceService;

/**
 * 全局共享的 handler 资源对象，该holder持有的handler{@link Handler}集合都是等待用户的
 * 发起的event{@link StatusChangedEvent}。
 * 1，如果该handler已处理event{@link StatusChangedEvent},则从队里中移除该handler；
 * 2，所有设备请求过来的handler都会按策略加入到该holder集合中{@link DeviceService}；
 * 3，当收到用户发起的event{@link StatusChangedEvent}，
 * 事件订阅者subscribe将会从该holder中获取对应的handler来处理对应事件。
 */
public interface IDeviceHandlerHolder<T> {

    T putHandler(DeviceId deviceId, T handler);

    T fetchHandler(DeviceId deviceId);
}
