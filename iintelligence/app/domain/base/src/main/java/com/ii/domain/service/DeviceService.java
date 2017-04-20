package com.ii.domain.service;

import com.ii.domain.handler.Handler;

/**
 * Created by liyou on 17/4/19.
 */
public interface DeviceService<T> {

    void registerDevice(T device);

}
