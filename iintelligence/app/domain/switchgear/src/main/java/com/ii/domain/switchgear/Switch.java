package com.ii.domain.switchgear;

import com.ii.domain.base.DeviceId;
import com.ii.domain.base.Entity;
import com.ii.domain.base.State;
import com.ii.domain.base.ValueObject;

/**
 * Created by liyou on 17/4/17.
 */
public class Switch implements Entity<Switch>{

    private DeviceId deviceId;

    private SwitchState state;

    public Switch(DeviceId deviceId, SwitchState state) {
        this.deviceId = deviceId;
        this.state = state;
    }

    public void deviceId(DeviceId deviceId){
        this.deviceId = deviceId;
    }
    public DeviceId deviceId(){
        return deviceId;
    }

    public void state(SwitchState state){
        this.state = state;
    }
    public SwitchState state(){
        return this.state;
    }

    public boolean sameStateAs(Switch other) {
        return other == null ? false :
                this.sameIdentityAs(other) && this.state().sameStateAs(other.state());
    }

    @Override
    public boolean sameIdentityAs(Switch other) {
        if(this == other || this.deviceId.sameIdentityAs(other.deviceId))
            return true;
        return false;
    }

}
