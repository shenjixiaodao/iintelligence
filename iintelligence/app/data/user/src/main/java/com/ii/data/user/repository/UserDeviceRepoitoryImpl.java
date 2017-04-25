package com.ii.data.user.repository;

import com.ii.data.user.mapper.UserDeviceMapper;
import com.ii.domain.base.DeviceType;
import com.ii.domain.repository.UserDeviceRepository;
import com.ii.domain.user.UserDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Map<String, String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("type", type.toString());
        return userDeviceMapper.find(map);
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
    public void update(UserDevice userDevice) {
        userDeviceMapper.update(userDevice);
    }
}
