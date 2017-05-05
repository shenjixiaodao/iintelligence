package com.ii.data.user.criteria;

/**
 * Created by liyou on 17/4/26.
 */
public class UserDeviceCriteria {
    private String uid;
    private String deviceType;
    private String deviceBindingStatus;

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

    public String getDeviceBindingStatus() {
        return deviceBindingStatus;
    }

    public void setDeviceBindingStatus(String deviceBindingStatus) {
        this.deviceBindingStatus = deviceBindingStatus;
    }
}
