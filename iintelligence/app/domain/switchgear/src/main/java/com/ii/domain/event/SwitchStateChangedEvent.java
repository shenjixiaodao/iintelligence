package com.ii.domain.event;


import com.ii.domain.switchgear.Switch;

/**
 * Created by liyou on 17/4/18.
 */
public class SwitchStateChangedEvent implements StateChangedEvent<SwitchStateChangedEvent> {

    private final Switch s;

    public Switch getSwitch(){
        return s;
    }

    public SwitchStateChangedEvent(Switch s) {
        this.s = s;
    }

    @Override
    public boolean sameEventAs(SwitchStateChangedEvent other) {
        return false;
    }

    @Override
    public SwitchStateChangedEvent getEvent() {
        return this;
    }
}
