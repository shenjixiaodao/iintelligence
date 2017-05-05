package com.ii.domain.user;

import com.ii.domain.base.Device;
import com.ii.domain.base.Entity;


/**
 * Created by liyou on 17/4/24.
 */
public class UserDevice implements Entity<UserDevice>{

    private UserId userId;
    private Device device;

    public UserDevice(UserId userId, Device device) {
        this.userId = userId;
        this.device = device;
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

    @Override
    public boolean sameIdentityAs(UserDevice other) {
        if(this == other ||
                (userId.equals(other.userId()) && device.sameIdentityAs(other.device())))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "UserDevice{" +
                "userId=" + userId +
                ", common=" + device +
                '}';
    }

    public UserDevice() {
        //for ORM
    }
}
