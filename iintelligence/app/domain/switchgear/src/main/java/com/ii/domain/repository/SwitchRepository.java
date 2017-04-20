package com.ii.domain.repository;


import com.ii.domain.switchgear.Switch;

/**
 * Created by liyou on 17/4/18.
 */
public interface SwitchRepository extends DeviceRepository<Switch> {
    @Override
    void find(Switch device);

    @Override
    void add(Switch device);

    @Override
    void upate(Switch device);
}
