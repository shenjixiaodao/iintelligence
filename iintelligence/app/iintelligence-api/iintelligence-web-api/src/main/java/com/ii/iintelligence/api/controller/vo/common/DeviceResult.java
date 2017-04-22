package com.ii.iintelligence.api.controller.vo.common;

import com.ii.iintelligence.api.controller.result.WebResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liyou on 17/4/22.
 */
public class DeviceResult extends WebResult {

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
