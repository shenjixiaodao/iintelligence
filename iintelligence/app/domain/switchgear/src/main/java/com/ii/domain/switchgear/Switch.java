package com.ii.domain.switchgear;

import com.ii.domain.base.*;

/**
 * Created by liyou on 17/4/17.
 */
public class Switch extends Device {

    private SwitchState state;

    public Switch(DeviceId deviceId, SwitchState state) {
        super(deviceId, DeviceType.Switch);
        this.state = state;
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

}
