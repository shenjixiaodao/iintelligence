package com.ii.domain.base.event;

import com.ii.domain.base.DomainEvent;

/**
 * Created by liyou on 17/4/19.
 */
public interface ChangeStatusOKEvent<T> extends DomainEvent<T>{

    T getEvent();

}
