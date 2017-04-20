package com.ii.domain.handler;


import com.ii.domain.base.DeviceId;

public interface ISwitchHandlerHolder extends IDeviceHandlerHolder<SwitchHandler>{

    @Override
    SwitchHandler putHandler(DeviceId deviceId, SwitchHandler handler);

    @Override
    SwitchHandler fetchHandler(DeviceId deviceId);
}
