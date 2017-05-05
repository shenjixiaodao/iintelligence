package com.ii.domain.user;

import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceType;
import com.ii.domain.base.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyou on 17/4/24.
 */
public class User implements Entity<User>{

    private UserId userId;
    private List<Device> devices;

    public UserId userId() {
        return this.userId;
    }
    public void userId(UserId userId){
        this.userId = userId;
    }

    public List<Device> devices(){
        return devices;
    }
    public void devices(List<Device> devices){
        this.devices = devices;
    }

    public int deviceLimit(DeviceType deviceType){
        //todo 设备数约束
        return 10;
    }

    @Deprecated
    public User() {
        //for ORM
        devices = new ArrayList<>();
    }

    @Override
    public boolean sameIdentityAs(User other) {
        if(this == other || userId.equals(other.userId()))
            return true;
        return false;
    }

}
