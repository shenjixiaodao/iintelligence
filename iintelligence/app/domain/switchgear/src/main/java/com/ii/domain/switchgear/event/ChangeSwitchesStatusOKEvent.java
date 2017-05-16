package com.ii.domain.switchgear.event;


import com.ii.domain.base.EventType;
import com.ii.domain.base.event.ChangeStatusOKEvent;
import com.ii.domain.switchgear.GroupSwitch;
import com.ii.domain.switchgear.Switch;

import java.util.List;

/**
 * Created by liyou on 17/4/18.
 */
public class ChangeSwitchesStatusOKEvent implements ChangeStatusOKEvent<ChangeSwitchesStatusOKEvent> {

    private final GroupSwitch ss;

    public GroupSwitch getSwitches(){
        return ss;
    }

    public ChangeSwitchesStatusOKEvent(GroupSwitch ss) {
        this.ss = ss;
    }

    @Override
    public boolean sameEventAs(ChangeSwitchesStatusOKEvent other) {
        return false;
    }

    @Override
    public EventType getEventType() {
        return EventType.OK;
    }

    @Override
    public ChangeSwitchesStatusOKEvent getEvent() {
        return this;
    }
}
