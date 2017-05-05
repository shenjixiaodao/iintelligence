package com.ii.biz.common.service;


import com.ii.biz.common.util.IdGenerator;
import com.ii.data.user.criteria.UserDeviceCriteria;
import com.ii.data.user.mapper.UserDeviceMapper;
import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.DeviceType;
import com.ii.domain.repository.UserDeviceRepository;
import com.ii.domain.service.DeviceService;
import com.ii.domain.user.UserDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyou on 17/4/22.
 */
@Service
public class BaseDeviceService implements DeviceService<Device> {

    @Autowired
    private UserDeviceRepository userDeviceRepository;

    @Autowired
    private UserDeviceMapper userDeviceMapper;

    @Override
    public void registerDevice(Device device) {
        //更新用户设备绑定状态，表明设备已激活
        userDeviceRepository.updateDeviceStatus(device.deviceId(), UserDevice.DeviceStatus.Binding);
        //fixme 注册设备基本信息
    }

    @Override
    public DeviceId fetchDeviceId(DeviceType type, String uid) {
        return new DeviceId(IdGenerator.generate(type, uid.substring(uid.length() - 2)));
    }

    @Override
    public List<Device> findRegisteredDevice(DeviceType type) {
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        criteria.setDeviceType(type.toString());
        criteria.setDeviceStatus(UserDevice.DeviceStatus.Binding.toString());
        List<UserDevice> userDevices = userDeviceMapper.find(criteria);
        List<Device> devices = new ArrayList<>(userDevices.size());
        for(UserDevice userDevice : userDevices) {
            devices.add(userDevice.device());
        }
        return devices;
    }
}
