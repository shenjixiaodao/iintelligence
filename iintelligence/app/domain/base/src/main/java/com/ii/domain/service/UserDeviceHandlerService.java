package com.ii.domain.service;

/**
 * Created by liyou on 17/4/19.
 */
public interface UserDeviceHandlerService<T> {

    /**
     * 用户请求触发设备更改状态指令
     * @param handler
     */
    void registerStateCommandHandler(final T handler);

}
