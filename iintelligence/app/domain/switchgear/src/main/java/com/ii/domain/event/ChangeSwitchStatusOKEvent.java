package com.ii.domain.event;


import com.ii.domain.switchgear.Switch;

/**
 * Created by liyou on 17/4/18.
 */
public class ChangeSwitchStatusOKEvent implements ChangeStatusOKEvent<ChangeSwitchStatusOKEvent> {

    private final Switch s;

    public ChangeSwitchStatusOKEvent(Switch s) {
        this.s = s;
    }

    public Switch getSwitch(){
        return s;
    }

    @Override
    public boolean sameEventAs(ChangeSwitchStatusOKEvent other) {
        return false;
    }

    @Override
    public ChangeSwitchStatusOKEvent getEvent() {
        return this;
    }
}