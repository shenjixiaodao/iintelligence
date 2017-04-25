package com.ii.domain.repository;

import com.ii.domain.base.DeviceType;
import com.ii.domain.user.UserDevice;

import java.util.List;

/**
 * Created by liyou on 17/4/24.
 */
public interface UserDeviceRepository {

    /**
     * 同一个用户同一种设备类型，只允许创建有限个
     * @param uid
     * @param type
     * @return
     */
    List<UserDevice> find(String uid, DeviceType type);

    /**
     * 设备数约束
     * @param uid
     * @param type
     * @return
     */
    int deviceLimit(String uid, DeviceType type);

    void add(UserDevice userDevice);

    void update(UserDevice userDevice);

}
