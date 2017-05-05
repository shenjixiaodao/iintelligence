package com.ii.biz.user.service.impl;

import com.ii.biz.common.service.BaseDeviceService;
import com.ii.biz.user.service.IUserDeviceService;
import com.ii.data.user.criteria.UserDeviceCriteria;
import com.ii.data.user.mapper.UserDeviceMapper;
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
    public List<Device> createUserDevice(String uid, DeviceType deviceType) {
        if(StringUtils.isEmpty(uid))
            throw new IllegalArgumentException("uid不允许空");
        if(deviceType == null)
            throw new IllegalArgumentException("deviceType不允许空");
        User user = userDeviceRepository.find(uid, deviceType);
        if(user.deviceLimit(deviceType) <= user.devices().size()) {
            //todo 设备数达到上限
            return user.devices();
        }
        UserId userId = new UserId(uid);
        Device device = new Device();
        device.type(deviceType);
        DeviceId deviceId = baseDeviceService.fetchDeviceId(deviceType, uid);
        device.deviceId(deviceId);
        device.bindingStatus(Device.BindingStatus.Binding);
        UserDevice userDevice = new UserDevice(userId,device);
        userDeviceRepository.add(userDevice);
        user.devices().add(device);

        return user.devices();
    }

}
