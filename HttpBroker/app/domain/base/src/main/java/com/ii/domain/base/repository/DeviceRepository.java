package com.ii.domain.base.repository;


import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceType;

import java.util.List;

/**
 * Created by liyou on 17/4/18.
 */
public interface DeviceRepository {

    void update(Device device);

    List<Device> find(DeviceType type, Device.BindingStatus bindingStatus);
}
