package com.ii.biz.common.service;


import com.ii.biz.common.util.IdGenerator;
import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.DeviceType;
import com.ii.domain.service.DeviceService;
import org.springframework.stereotype.Service;

/**
 * Created by liyou on 17/4/22.
 */
@Service
public class BaseDeviceService implements DeviceService<Device> {

    @Override
    public void registerDevice(Device device) {
        //todo 注册设备
    }

    @Override
    public DeviceId fetchDeviceId(DeviceType type, String uid) {
        return new DeviceId(IdGenerator.generate(type, uid.substring(uid.length() - 2)));
    }
}
