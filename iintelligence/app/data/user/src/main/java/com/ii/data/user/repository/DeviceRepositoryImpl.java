package com.ii.data.user.repository;

import com.ii.data.user.criteria.UserDeviceCriteria;
import com.ii.data.user.mapper.UserDeviceMapper;
import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceType;
import com.ii.domain.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 2017/5/5.
 */
public class DeviceRepositoryImpl implements DeviceRepository {

    @Autowired
    private UserDeviceMapper userDeviceMapper;

    @Override
    public void update(Device device) {
        Map<String, String> map = new HashMap<>();
        map.put("deviceId",device.deviceId().id());
        map.put("bindingStatus", device.bindingStatus().toString());
        userDeviceMapper.updateDeviceBindingStatus(map);
    }

    @Override
    public List<Device> find(DeviceType type, Device.BindingStatus bindingStatus) {
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        criteria.setDeviceType(type.toString());
        criteria.setDeviceBindingStatus(Device.BindingStatus.Binding.toString());
        List<Device> devices = userDeviceMapper.findDevices(criteria);
        return devices;
    }
}
