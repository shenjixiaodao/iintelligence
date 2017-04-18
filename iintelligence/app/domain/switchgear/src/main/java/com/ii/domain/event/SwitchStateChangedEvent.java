package com.ii.domain.event;

import com.ii.domain.base.DomainEvent;

/**
 * Created by liyou on 17/4/18.
 */
public class SwitchStateChangedEvent implements DomainEvent<SwitchStateChangedEvent> {
    @Override
    public boolean sameEventAs(SwitchStateChangedEvent other) {
        return false;
    }
}
