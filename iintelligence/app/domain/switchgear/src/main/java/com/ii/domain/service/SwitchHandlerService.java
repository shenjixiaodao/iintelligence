package com.ii.domain.service;

import com.ii.domain.handler.SwitchHandler;

/**
 * Created by liyou on 17/4/19.
 */
public interface SwitchHandlerService extends DeviceHandlerService<SwitchHandler> {

    @Override
    void registerStateChangedHandler(final SwitchHandler handler);
}
