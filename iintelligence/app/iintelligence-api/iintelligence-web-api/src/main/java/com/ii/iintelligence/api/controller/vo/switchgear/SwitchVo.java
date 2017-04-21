package com.ii.iintelligence.api.controller.vo.switchgear;

import com.ii.iintelligence.api.controller.result.WebResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public class SwitchVo{

    @ApiModelProperty(value = "设备ID")
    private String deviceId;
    @ApiModelProperty(value = "设备状态")
    private String state;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SwitchResult{" +
                "deviceId='" + deviceId + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
