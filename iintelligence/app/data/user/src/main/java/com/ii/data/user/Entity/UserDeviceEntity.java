package com.ii.data.user.Entity;

/**
 * Created by liyou on 17/4/26.
 */
public class UserDeviceEntity {

    private String uid;
    private String deviceId;
    private String deviceType;
    private String deviceBindingStatus;

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

    public String getDeviceBindingStatus() {
        return deviceBindingStatus;
    }

    public void setDeviceBindingStatus(String deviceBindingStatus) {
        this.deviceBindingStatus = deviceBindingStatus;
    }
}
