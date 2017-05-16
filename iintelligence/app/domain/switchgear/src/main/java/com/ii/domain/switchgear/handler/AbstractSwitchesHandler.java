package com.ii.domain.switchgear.handler;

import com.ect.common.error.Result;
import com.ii.domain.switchgear.GroupSwitch;
import com.ii.domain.switchgear.Switch;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by liyou on 17/4/27.
 */
public abstract class AbstractSwitchesHandler implements SwitchesHandler {

    private final AtomicBoolean isHandled;
    private final GroupSwitch switches;

    public AbstractSwitchesHandler(GroupSwitch switches) {
        isHandled = new AtomicBoolean(false);
        this.switches = switches;
    }

    public abstract void doResultReadyEvent(Result result);

    @Override
    public void resultReadyEvent(final Result result) {
        if(isHandled() || isHandled.getAndSet(true))
            return;
        doResultReadyEvent(result);
    }

    @Override
    public GroupSwitch getSwitches() {
        return this.switches;
    }

    @Override
    public boolean isHandled() {
        return isHandled.get();
    }
}
