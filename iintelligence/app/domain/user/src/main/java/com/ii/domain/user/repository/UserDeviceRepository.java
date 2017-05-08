package com.ii.domain.user.repository;

import com.ii.domain.base.DeviceType;
import com.ii.domain.user.criteria.UserDeviceCriteria;
import com.ii.domain.user.User;
import com.ii.domain.user.UserDevice;

import javax.annotation.Nullable;

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
    User find(String uid, @Nullable DeviceType type);

    /**
     * 多个条件查询
     * @param criteria
     * @return
     */
    User find(UserDeviceCriteria criteria);

    /**
     * 设备数约束
     * @param uid
     * @param type
     * @return
     */
    int deviceLimit(String uid, DeviceType type);

    void add(UserDevice userDevice);

}
