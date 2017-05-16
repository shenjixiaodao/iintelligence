package com.ii.domain.switchgear;

import com.ii.domain.base.*;

/**
 * Created by liyou on 17/4/17.
 */
public class Switch extends Device {

    private SwitchStatus status;

    public Switch(DeviceId deviceId, SwitchStatus status) {
        super(deviceId, DeviceType.Switch);
        this.status = status;
    }

    public void status(SwitchStatus state){
        this.status = state;
    }
    public SwitchStatus status(){
        return this.status;
    }

    public boolean sameStatusAs(Switch other) {
        return other == null ? false :
                this.sameIdentityAs(other) && this.status().sameStatusAs(other.status());
    }

    @Deprecated
    public Switch() {
        //for ORM
        type(DeviceType.Switch);
    }
}
