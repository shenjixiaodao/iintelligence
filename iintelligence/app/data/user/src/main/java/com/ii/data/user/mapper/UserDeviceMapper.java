package com.ii.data.user.mapper;

import com.ii.data.user.Entity.UserDeviceEntity;
import com.ii.data.user.criteria.UserDeviceCriteria;
import com.ii.domain.user.UserDevice;

import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 17/4/24.
 */
public interface UserDeviceMapper {

    void add(UserDevice userDevice);

    void updateDeviceStatus(Map map);

    List<UserDevice> find(UserDeviceCriteria criteria);

    List<UserDeviceEntity> queryUserDevice(UserDeviceCriteria criteria);
}
