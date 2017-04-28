package com.ii.data.user.query;

import com.ii.data.user.mapper.UserDeviceMapper;
import com.ii.data.user.query.Entity.UserDeviceEntity;
import com.ii.data.user.query.criteria.UserDeviceCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyou on 17/4/26.
 */
@Service
public class UserDeviceQueryManagement {

    @Autowired
    private UserDeviceMapper userDeviceMapper;

    public List<UserDeviceEntity> queryUserDevice(UserDeviceCriteria criteria){
        return userDeviceMapper.queryUserDevice(criteria);
    }

}
