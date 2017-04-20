package com.ii.domain.event;

import com.ii.domain.base.DomainEvent;

/**
 * Created by liyou on 17/4/19.
 */
public interface StateChangedEvent<T> extends DomainEvent<T>{

    T getEvent();

}
