package com.ii.domain.event;


import com.ii.domain.switchgear.Switch;

import java.util.List;

/**
 * Created by liyou on 17/4/18.
 */
public class SwitchesStatusChangedEvent implements StatusChangedEvent<SwitchesStatusChangedEvent> {

    private final List<Switch> ss;

    public List<Switch> getSwitches(){
        return ss;
    }

    public SwitchesStatusChangedEvent(List<Switch> ss) {
        this.ss = ss;
    }

    @Override
    public boolean sameEventAs(SwitchesStatusChangedEvent other) {
        return false;
    }

    @Override
    public SwitchesStatusChangedEvent getEvent() {
        return this;
    }
}
