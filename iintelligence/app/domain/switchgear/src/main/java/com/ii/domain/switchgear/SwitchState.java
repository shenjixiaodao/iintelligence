package com.ii.domain.switchgear;

import com.ii.domain.base.DeviceId;
import com.ii.domain.base.Entity;
import com.ii.domain.base.State;
import com.ii.domain.base.ValueObject;

/**
 * Created by liyou on 17/4/17.
 */
public class SwitchState implements Entity<SwitchState>, ValueObject<SwitchState>, State<SwitchState>{

    private DeviceId deviceId;

    private State state;

    enum State{
        ON("打开"), OFF("关闭");
        private String text;
        State(String text){
            this.text = text;
        }
        public String getText(){
            return this.text;
        }
    }

    public void deviceId(DeviceId deviceId){
        this.deviceId = deviceId;
    }
    public DeviceId deviceId(){
        return deviceId;
    }

    public void state(State state){
        this.state = state;
    }
    public State state(){
        return this.state;
    }

    @Override
    public boolean sameStateAs(SwitchState other) {
        return this.sameIdentityAs(other) && this.sameValueAs(other);
    }

    @Override
    public boolean sameIdentityAs(SwitchState other) {
        if(this == other || this.deviceId.sameIdentityAs(other.deviceId))
            return true;
        return false;
    }

    @Override
    public boolean sameValueAs(SwitchState other) {
        if(this.state == other.state())
            return true;
        return false;
    }
}
