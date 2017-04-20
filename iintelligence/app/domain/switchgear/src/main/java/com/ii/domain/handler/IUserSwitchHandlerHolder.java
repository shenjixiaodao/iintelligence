package com.ii.domain.handler;


import com.ii.domain.base.DeviceId;

public interface IUserSwitchHandlerHolder extends IDeviceHandlerHolder<UserSwitchHandler>{
    @Override
    UserSwitchHandler putHandler(DeviceId deviceId, UserSwitchHandler handler);

    @Override
    UserSwitchHandler fetchHandler(DeviceId deviceId);
}
