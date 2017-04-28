package com.ii.biz.common.service;


import com.ii.biz.common.util.IdGenerator;
import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.DeviceType;
import com.ii.domain.repository.UserDeviceRepository;
import com.ii.domain.service.DeviceService;
import com.ii.domain.user.UserDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyou on 17/4/22.
 */
@Service
public class BaseDeviceService implements DeviceService<Device> {

    @Autowired
    private UserDeviceRepository userDeviceRepository;

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
}
