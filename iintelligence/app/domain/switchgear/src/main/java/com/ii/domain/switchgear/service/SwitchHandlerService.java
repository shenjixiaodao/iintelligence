package com.ii.domain.switchgear.service;

import com.ii.domain.base.service.ContinuationService;
import com.ii.domain.switchgear.handler.SwitchHandler;
import com.ii.domain.switchgear.handler.SwitchesHandler;
import com.ii.domain.switchgear.event.SwitchesStatusChangedEvent;

/**
 * Created by liyou on 17/4/19.
 */
public interface SwitchHandlerService extends ContinuationService<SwitchHandler> {

    @Override
    void registerStatusEventHandler(final SwitchHandler handler);

    /**
     * 同时注册多台开关设备状态处理handler， 订阅事件{@link SwitchesStatusChangedEvent}
     * @param handler 多台开关设备关联同一个handler
     */
    void registerStatusEventHandler(final SwitchesHandler handler);
}
