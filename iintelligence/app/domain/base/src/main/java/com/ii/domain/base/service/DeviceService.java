package com.ii.domain.base.service;


import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.DeviceType;
import com.ii.domain.base.GroupId;

import java.util.List;

/**
 * Created by liyou on 17/4/19.
 */
public interface DeviceService<T> {

    /**
     * 完成设备激活、基本信息登记等
     * @param device
     */
    void registerDevice(T device);

    DeviceId fetchDeviceId(DeviceType type, String uid);

    GroupId fetchGroupId(String uid);

    /**
     * 按设备类型查找已注册的设备
     * @param type
     * @return
     */
    List<Device> findRegisteredDevice(DeviceType type);
}
