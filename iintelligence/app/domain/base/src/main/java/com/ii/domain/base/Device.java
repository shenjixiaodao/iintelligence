package com.ii.domain.base;

/**
 * Created by liyou on 17/4/22.
 */
public abstract class Device implements Entity<Device> {
    private DeviceId deviceId;
    private DeviceType type;

    public Device(DeviceId deviceId, DeviceType type) {
        this.deviceId = deviceId;
        this.type = type;
    }

    public void type(DeviceType type){
        this.type = type;
    }
    public DeviceType type(){
        return this.type;
    }

    public void deviceId(DeviceId deviceId){
        this.deviceId = deviceId;
    }
    public DeviceId deviceId(){
        return deviceId;
    }

    @Override
    public boolean sameIdentityAs(Device other) {
        if(this == other || this.deviceId.sameIdentityAs(other.deviceId))
            return true;
        return false;
    }

}
