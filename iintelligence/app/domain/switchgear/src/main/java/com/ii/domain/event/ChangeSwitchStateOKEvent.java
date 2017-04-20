package com.ii.domain.event;


import com.ii.domain.switchgear.Switch;

/**
 * Created by liyou on 17/4/18.
 */
public class ChangeSwitchStateOKEvent implements ChangeStateOKEvent<ChangeSwitchStateOKEvent> {

    private final Switch s;

    public ChangeSwitchStateOKEvent(Switch s) {
        this.s = s;
    }

    public Switch getSwitch(){
        return s;
    }

    @Override
    public boolean sameEventAs(ChangeSwitchStateOKEvent other) {
        return false;
    }

    @Override
    public ChangeSwitchStateOKEvent getEvent() {
        return this;
    }
}
