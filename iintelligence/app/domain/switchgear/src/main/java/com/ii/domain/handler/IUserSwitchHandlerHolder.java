package com.ii.domain.handler;


import com.ii.domain.base.DeviceId;

public interface IUserSwitchHandlerHolder extends IDeviceHandlerHolder<Handler>{
    @Override
    Handler putHandler(DeviceId deviceId, Handler handler);

    @Override
    Handler fetchHandler(DeviceId deviceId);
}
