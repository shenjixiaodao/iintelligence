package com.ii.domain.service;

import com.ii.domain.handler.SwitchHandler;
import com.ii.domain.handler.SwitchesHandler;
import com.ii.domain.event.SwitchesStatusChangedEvent;

/**
 * Created by liyou on 17/4/19.
 */
public interface SwitchHandlerService extends DeviceHandlerService<SwitchHandler> {

    @Override
    void registerStatusChangedHandler(final SwitchHandler handler);

    /**
     * 同时注册多台开关设备状态处理handler， 等待事件{@link SwitchesStatusChangedEvent}
     * @param handler 多台开关设备关联同一个handler
     */
    void registerStatusChangedHandler(final SwitchesHandler handler);
}
