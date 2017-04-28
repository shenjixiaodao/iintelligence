package com.ii.domain.handler;


import com.ii.domain.base.DeviceId;

public interface ISwitchHandlerHolder extends IDeviceHandlerHolder<Handler>{

    Handler getHandler(DeviceId deviceId);

    @Override
    Handler putHandler(DeviceId deviceId, Handler handler);

    @Override
    Handler fetchHandler(DeviceId deviceId);
}
