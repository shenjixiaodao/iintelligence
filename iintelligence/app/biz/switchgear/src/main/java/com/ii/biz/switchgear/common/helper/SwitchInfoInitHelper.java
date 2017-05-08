package com.ii.biz.switchgear.common.helper;

import com.ii.biz.switchgear.common.SwitchHandlerHolder;
import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceType;
import com.ii.domain.base.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 该类应该在spring容器创建完后自动实例化，并从持久数据中获取初始信息。
 * 1，使用{@table ii_user_device}中所有{@link com.ii.domain.base.Device.BindingStatus#Binding}状态的设备，
 *    用于初始化{@link SwitchHandlerHolder#handlerMap}中的元素。
 *
 * @author liyou
 */
@Component
public class SwitchInfoInitHelper {


    private DeviceService deviceService;

    @Autowired
    public SwitchInfoInitHelper(DeviceService deviceService){
        this.deviceService = deviceService;
        loadAndInit();
    }

    private void loadAndInit(){
        List<Device> devices = deviceService.findRegisteredDevice(DeviceType.Switch);
        SwitchHandlerHolder.init(devices);
    }
}
