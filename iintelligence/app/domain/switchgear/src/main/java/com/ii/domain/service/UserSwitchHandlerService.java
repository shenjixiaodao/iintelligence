package com.ii.domain.service;


import com.ii.domain.handler.UserSwitchHandler;
import com.ii.domain.handler.UserSwitchesHandler;
import com.ii.domain.event.SwitchesStatusChangedEvent;

/**
 * Created by liyou on 17/4/19.
 */
public interface UserSwitchHandlerService extends UserDeviceHandlerService<UserSwitchHandler> {
    @Override
    void registerStatusCommandHandler(final UserSwitchHandler handler);

    /**
     * 同时发起一组开关的控制命令, 对应的会发布事件 {@link SwitchesStatusChangedEvent}
     * @param handler 一组开关关联同一个handler
     */
    void registerStatusCommandHandler(final UserSwitchesHandler handler);
}
