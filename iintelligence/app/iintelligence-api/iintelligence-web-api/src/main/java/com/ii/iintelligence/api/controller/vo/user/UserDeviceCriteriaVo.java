package com.ii.iintelligence.api.controller.vo.user;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liyou on 17/4/26.
 */
public class UserDeviceCriteriaVo {
    @ApiModelProperty(value = "用户ID", required = true)
    private String uid;
    @ApiModelProperty(value = "用户ID")
    private String deviceType;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return "UserDeviceCriteriaVo{" +
                "uid='" + uid + '\'' +
                ", deviceType='" + deviceType + '\'' +
                '}';
    }
}
