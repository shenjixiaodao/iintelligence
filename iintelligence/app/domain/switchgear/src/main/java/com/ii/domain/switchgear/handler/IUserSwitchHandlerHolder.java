package com.ii.domain.switchgear.handler;


import com.ii.domain.base.DeviceId;
import com.ii.domain.base.handler.Handler;
import com.ii.domain.base.handler.IDeviceHandlerHolder;

public interface IUserSwitchHandlerHolder extends IDeviceHandlerHolder<Handler> {
    @Override
    Handler putHandler(DeviceId deviceId, Handler handler);

    @Override
    Handler fetchHandler(DeviceId deviceId);
}
