package com.ii.domain.base.service;

/**
 * Created by liyou on 17/4/19.
 */
public interface DeviceHandlerService<T> {

    void registerStatusEventHandler(final T handler);

}
