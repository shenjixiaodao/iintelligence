package com.ii.domain.service;

/**
 * Created by liyou on 17/4/19.
 */
public interface DeviceHandlerService<T> {

    void registerStateChangedHandler(final T handler);

}
