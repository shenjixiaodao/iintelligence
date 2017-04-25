package com.ii.domain.user;

import com.ii.domain.base.DeviceType;

/**
 * Created by liyou on 17/4/24.
 */
public class User {

    private UserId userId;
    private String name;

    public int deviceLimit(DeviceType deviceType){
        //todo 设备数约束
        return 10;
    }

    @Deprecated
    public User() {
        //for ORM
    }
}
