package com.ii.domain.repository;



/**
 * Created by liyou on 17/4/18.
 */
public interface DeviceRepository<T> {

    void find(T device);

    void add(T device);

    void upate(T device);

}
