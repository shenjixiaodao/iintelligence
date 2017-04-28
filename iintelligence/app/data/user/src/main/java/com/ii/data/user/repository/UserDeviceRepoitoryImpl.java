package com.ii.data.user.repository;

import com.ii.data.user.mapper.UserDeviceMapper;
import com.ii.data.user.criteria.UserDeviceCriteria;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.DeviceType;
import com.ii.domain.repository.UserDeviceRepository;
import com.ii.domain.user.UserDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 17/4/24.
 */
@Repository
public class UserDeviceRepoitoryImpl implements UserDeviceRepository{

    @Autowired
    private UserDeviceMapper userDeviceMapper;

    @Override
    public List<UserDevice> find(String uid, DeviceType type) {
        if(StringUtils.isEmpty(uid))
            throw new IllegalArgumentException("uid不允许为空");
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        criteria.setUid(uid);
        criteria.setDeviceType(type.toString());
        return userDeviceMapper.find(criteria);
    }

    @Override
    public int deviceLimit(String uid, DeviceType type) {
        return 0;
    }

    @Override
    public void add(UserDevice userDevice) {
        userDeviceMapper.add(userDevice);
    }

    @Override
    public void updateDeviceStatus(DeviceId deviceId, UserDevice.DeviceStatus status) {
        Map<String, String> map = new HashMap<>();
        map.put("deviceId",deviceId.id());
        map.put("status", status.toString());
        userDeviceMapper.updateDeviceStatus(map);
    }
}
