package com.ii.domain.switchgear.handler;


import com.ii.domain.base.DeviceId;
import com.ii.domain.base.handler.Handler;

public interface IUserSwitchHandlerHolder{

    Handler putHandler(DeviceId deviceId, Handler handler);


    Handler fetchHandler(DeviceId deviceId);
}
