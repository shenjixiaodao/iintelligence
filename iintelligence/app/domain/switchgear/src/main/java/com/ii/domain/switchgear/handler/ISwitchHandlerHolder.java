package com.ii.domain.switchgear.handler;


import com.ii.domain.base.DeviceId;
import com.ii.domain.base.handler.Handler;
import com.ii.domain.base.handler.IDeviceHandlerHolder;

public interface ISwitchHandlerHolder extends IDeviceHandlerHolder<Handler> {

    Handler getHandler(DeviceId deviceId);

    @Override
    Handler putHandler(DeviceId deviceId, Handler handler);

    @Override
    Handler fetchHandler(DeviceId deviceId);
}
