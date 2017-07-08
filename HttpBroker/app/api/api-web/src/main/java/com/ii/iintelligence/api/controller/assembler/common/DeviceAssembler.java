package com.ii.iintelligence.api.controller.assembler.common;

import com.ii.domain.base.Device;
import com.ii.iintelligence.api.controller.vo.common.DeviceVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyou on 2017/5/5.
 */
public class DeviceAssembler {


    public static DeviceVo toVo(Device device){
        DeviceVo vo = new DeviceVo();
        vo.setDeviceId(device.deviceId().id());
        vo.setType(device.type().toString());
        vo.setBindingStatus(device.bindingStatus().toString());
        return vo;
    }

    public static List<DeviceVo> toVoList(List<Device> devices){
        List<DeviceVo> voList = new ArrayList<>(devices.size());
        for(Device device : devices){
            DeviceVo vo = toVo(device);
            voList.add(vo);
        }
        return voList;
    }

}
