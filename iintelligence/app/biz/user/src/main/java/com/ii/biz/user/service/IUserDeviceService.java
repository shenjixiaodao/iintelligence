package com.ii.biz.user.service;

import com.ii.domain.base.DeviceType;
import com.ii.domain.user.UserDevice;

import java.util.List;

/**
 * Created by liyou on 17/4/24.
 */
public interface IUserDeviceService {

    /**
     * 设备创建规则要符合幂等控制要求， 同一个同一设备类型只能创建有限制数量的设备;
     * 如果创建设备达到上限, 则无法创建成功
     * @param uid
     * @param deviceType
     * @return 当前用户创建的对应{@param deviceType}的设备
     */
    List<UserDevice> createUserDevice(String uid, DeviceType deviceType);

}
