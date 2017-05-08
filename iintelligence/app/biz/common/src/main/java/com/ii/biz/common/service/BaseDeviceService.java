package com.ii.biz.common.service;


import com.ii.biz.common.util.IdGenerator;
import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.DeviceType;
import com.ii.domain.base.repository.DeviceRepository;
import com.ii.domain.base.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyou on 17/4/22.
 */
@Service
public class BaseDeviceService implements DeviceService<Device> {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public void registerDevice(Device device) {
        //更新用户设备绑定状态，表明设备已激活
        device.bindingStatus(Device.BindingStatus.Binding);
        deviceRepository.update(device);
        //fixme 注册设备基本信息
    }

    @Override
    public DeviceId fetchDeviceId(DeviceType type, String uid) {
        return new DeviceId(IdGenerator.generate(type, uid.substring(uid.length() - 2)));
    }

    @Override
    public List<Device> findRegisteredDevice(DeviceType type) {
        return deviceRepository.find(type, Device.BindingStatus.Binding);
    }
}
