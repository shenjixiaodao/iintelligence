package com.ii.domain.base;

/**
 * Created by liyou on 17/4/22.
 */
public class Device implements Entity<Device> {
    private DeviceId deviceId;
    private DeviceType type;
    private BindingStatus bindingStatus;

    public Device(DeviceId deviceId, DeviceType type) {
        this.deviceId = deviceId;
        this.type = type;
        bindingStatus = BindingStatus.Unknown;
    }

    public enum BindingStatus {
        Created("创建"), Binding("绑定设备"), Unknown("未知");
        private String text;

        BindingStatus(String text){
            this.text = text;
        }

        public String getText() {
            return text;
        }
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

    public void bindingStatus(Device.BindingStatus bindingStatus){
        this.bindingStatus = bindingStatus;
    }
    public Device.BindingStatus bindingStatus(){
        return this.bindingStatus;
    }

    @Override
    public boolean sameIdentityAs(Device other) {
        if(this == other || this.deviceId.sameIdentityAs(other.deviceId))
            return true;
        return false;
    }

    public Device() {
        //for ORM
    }
}
