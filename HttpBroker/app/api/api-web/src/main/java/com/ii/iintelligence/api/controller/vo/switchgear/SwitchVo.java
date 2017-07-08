package com.ii.iintelligence.api.controller.vo.switchgear;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public class SwitchVo{

    @ApiModelProperty(value = "设备ID")
    private String deviceId;
    @ApiModelProperty(value = "设备状态")
    private String status;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SwitchResult{" +
                "deviceId='" + deviceId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
