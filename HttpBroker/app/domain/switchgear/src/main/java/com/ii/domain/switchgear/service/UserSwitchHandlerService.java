package com.ii.domain.switchgear.service;


import com.ii.domain.base.service.ContinuationService;
import com.ii.domain.switchgear.handler.UserSwitchEventHandler;

/**
 * Created by liyou on 17/4/19.
 */
public interface UserSwitchHandlerService extends ContinuationService<UserSwitchEventHandler> {
    @Override
    void registerStatusEventHandler(final UserSwitchEventHandler handler);
}
