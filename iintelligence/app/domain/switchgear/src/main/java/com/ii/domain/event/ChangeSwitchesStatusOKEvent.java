package com.ii.domain.event;


import com.ii.domain.switchgear.Switch;

import java.util.List;

/**
 * Created by liyou on 17/4/18.
 */
public class ChangeSwitchesStatusOKEvent implements StatusChangedEvent<ChangeSwitchesStatusOKEvent> {

    private final List<Switch> ss;

    public List<Switch> getSwitches(){
        return ss;
    }

    public ChangeSwitchesStatusOKEvent(List<Switch> ss) {
        this.ss = ss;
    }

    @Override
    public boolean sameEventAs(ChangeSwitchesStatusOKEvent other) {
        return false;
    }

    @Override
    public ChangeSwitchesStatusOKEvent getEvent() {
        return this;
    }
}
