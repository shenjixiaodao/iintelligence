package com.ii.biz.user.service.impl;

import com.ii.biz.common.service.BaseDeviceService;
import com.ii.biz.user.service.IUserDeviceService;
import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.DeviceType;
import com.ii.domain.repository.UserDeviceRepository;
import com.ii.domain.user.User;
import com.ii.domain.user.UserDevice;
import com.ii.domain.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by liyou on 17/4/24.
 */
@Service
public class UserDeviceServiceImpl implements IUserDeviceService {

    @Autowired
    private UserDeviceRepository userDeviceRepository;

    @Autowired
    private BaseDeviceService baseDeviceService;

    @Override
    public List<UserDevice> createUserDevice(String uid, DeviceType deviceType) {
        if(StringUtils.isEmpty(uid))
            throw new IllegalArgumentException("uid不允许空");
        if(deviceType == null)
            throw new IllegalArgumentException("deviceType不允许空");
        List<UserDevice> userDevices = userDeviceRepository.find(uid, deviceType);
        /**
         * todo 获取用户设备上限数, 判断是否可以创建新的设备
         */
        User user = new User();
        if(user.deviceLimit(deviceType) <= userDevices.size()) {
            //设备数达到上限
            return userDevices;
        }
        UserId userId = new UserId(uid);
        Device device = new Device();
        device.type(deviceType);
        DeviceId deviceId = baseDeviceService.fetchDeviceId(deviceType, uid);
        device.deviceId(deviceId);
        UserDevice userDevice = new UserDevice(userId,device, UserDevice.DeviceStatus.Create);
        userDeviceRepository.add(userDevice);
        userDevices.add(userDevice);

        return userDevices;
    }
}
