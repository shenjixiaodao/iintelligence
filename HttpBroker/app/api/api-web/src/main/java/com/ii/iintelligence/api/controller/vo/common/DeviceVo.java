package com.ii.iintelligence.api.controller.vo.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liyou on 2017/5/5.
 */
public class DeviceVo {
    @ApiModelProperty(value = "设备ID")
    private String deviceId;
    @ApiModelProperty(value = "设备类型")
    private String type;
    @ApiModelProperty(value = "绑定状态")
    private String bindingStatus;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBindingStatus() {
        return bindingStatus;
    }

    public void setBindingStatus(String bindingStatus) {
        this.bindingStatus = bindingStatus;
    }

    @Override
    public String toString() {
        return "DeviceVo{" +
                "deviceId='" + deviceId + '\'' +
                ", type='" + type + '\'' +
                ", bindingStatus='" + bindingStatus + '\'' +
                '}';
    }
}
