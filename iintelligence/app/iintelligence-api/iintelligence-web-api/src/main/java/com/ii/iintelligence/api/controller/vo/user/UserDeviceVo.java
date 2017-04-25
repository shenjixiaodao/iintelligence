package com.ii.iintelligence.api.controller.vo.user;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liyou on 17/4/25.
 */
public class UserDeviceVo {
    @ApiModelProperty(value = "用户ID", required = true)
    private String uid;
    @ApiModelProperty(value = "设备ID", required = true)
    private String deviceId;
    @ApiModelProperty(value = "设备类型", required = true)
    private String deviceType;
    @ApiModelProperty(value = "设备注册状态", required = true)
    private String deviceStatus;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    @Override
    public String toString() {
        return "UserDeviceVo{" +
                "uid='" + uid + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                '}';
    }
}
