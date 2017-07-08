package com.ii.domain.base.service;

/**
 * Created by liyou on 2017/5/11.
 */
public interface ContinuationService<T> {

    void registerStatusEventHandler(final T handler);

}
