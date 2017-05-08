package com.ii.data.user.repository;

import com.ii.data.user.mapper.UserDeviceMapper;
import com.ii.domain.user.criteria.UserDeviceCriteria;
import com.ii.domain.base.DeviceType;
import com.ii.domain.user.repository.UserDeviceRepository;
import com.ii.domain.user.User;
import com.ii.domain.user.UserDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by liyou on 17/4/24.
 */
@Repository
public class UserDeviceRepositoryImpl implements UserDeviceRepository{

    @Autowired
    private UserDeviceMapper userDeviceMapper;

    @Override
    public User find(String uid, DeviceType type) {
        if(StringUtils.isEmpty(uid))
            throw new IllegalArgumentException("uid不允许为空");
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        criteria.setUid(uid);
        criteria.setDeviceType(type.toString());
        return userDeviceMapper.findUser(criteria);
    }

    @Override
    public User find(UserDeviceCriteria criteria) {
        return userDeviceMapper.findUser(criteria);
    }

    @Override
    public int deviceLimit(String uid, DeviceType type) {
        return 0;
    }

    @Override
    public void add(UserDevice userDevice) {
        userDeviceMapper.add(userDevice);
    }

}
