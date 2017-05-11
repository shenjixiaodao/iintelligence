package com.ii.domain.switchgear.service;


import com.ii.domain.base.service.ContinuationService;
import com.ii.domain.switchgear.event.ChangeSwitchesStatusOKEvent;
import com.ii.domain.switchgear.handler.UserSwitchHandler;
import com.ii.domain.switchgear.handler.UserSwitchesHandler;
import com.ii.domain.switchgear.event.SwitchesStatusChangedEvent;

/**
 * Created by liyou on 17/4/19.
 */
public interface UserSwitchHandlerService extends ContinuationService<UserSwitchHandler> {
    @Override
    void registerStatusEventHandler(final UserSwitchHandler handler);

    /**
     * 同时发起一组开关的控制命令,发布事件 {@link SwitchesStatusChangedEvent},对应订阅{@link ChangeSwitchesStatusOKEvent}
     *
     * @param handler 一组开关关联同一个handler
     */
    void registerStatusEventHandler(final UserSwitchesHandler handler);
}
