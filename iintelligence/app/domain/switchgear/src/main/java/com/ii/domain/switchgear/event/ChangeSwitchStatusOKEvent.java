package com.ii.domain.switchgear.event;


import com.ii.domain.base.EventType;
import com.ii.domain.base.event.ChangeStatusOKEvent;
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
    public EventType getEventType() {
        return EventType.OK;
    }

    @Override
    public ChangeSwitchStatusOKEvent getEvent() {
        return this;
    }
}
