package com.ii.domain.service;

import com.ii.domain.handler.UserSwitchHandler;

/**
 * Created by liyou on 17/4/19.
 */
public interface UserSwitchHandlerService extends UserDeviceHandlerService<UserSwitchHandler> {
    @Override
    void registerStateCommandHandler(final UserSwitchHandler handler);
}
