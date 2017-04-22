package com.ii.domain.service;


import com.ii.domain.base.DeviceId;
import com.ii.domain.base.DeviceType;

/**
 * Created by liyou on 17/4/19.
 */
public interface DeviceService<T> {

    void registerDevice(T device);

    DeviceId fetchDeviceId(DeviceType type, String uid);
}
