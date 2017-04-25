package com.ii.domain.user;

import com.ii.domain.base.Device;
import com.ii.domain.base.Entity;


/**
 * Created by liyou on 17/4/24.
 */
public class UserDevice implements Entity<UserDevice>{

    private UserId userId;
    private Device device;
    private DeviceStatus status;

    public UserDevice(UserId userId, Device device, DeviceStatus status) {
        this.userId = userId;
        this.device = device;
        this.status = status;
    }

    public enum DeviceStatus {
        Create("创建"), Bind("绑定设备");
        private String text;

        DeviceStatus(String text){
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public UserId userId(){
        return userId;
    }
    public void userId(UserId userId){
        this.userId = userId;
    }

    public Device device(){
        return device;
    }
    public void device(Device device){
        this.device = device;
    }

    public DeviceStatus status(){
        return status;
    }
    public void status(DeviceStatus status){
        this.status = status;
    }

    @Override
    public boolean sameIdentityAs(UserDevice other) {
        if(this == other || device.sameIdentityAs(other.device()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "UserDevice{" +
                "userId=" + userId +
                ", device=" + device +
                ", status=" + status +
                '}';
    }

    public UserDevice() {
        //for ORM
    }
}
