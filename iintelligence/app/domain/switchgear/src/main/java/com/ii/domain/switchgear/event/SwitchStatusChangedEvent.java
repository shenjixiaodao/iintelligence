package com.ii.domain.switchgear.event;


import com.ii.domain.base.event.StatusChangedEvent;
import com.ii.domain.switchgear.Switch;

/**
 * Created by liyou on 17/4/18.
 */
public class SwitchStatusChangedEvent implements StatusChangedEvent<SwitchStatusChangedEvent> {

    private final Switch s;

    public Switch getSwitch(){
        return s;
    }

    public SwitchStatusChangedEvent(Switch s) {
        this.s = s;
    }

    @Override
    public boolean sameEventAs(SwitchStatusChangedEvent other) {
        return false;
    }

    @Override
    public SwitchStatusChangedEvent getEvent() {
        return this;
    }
}
