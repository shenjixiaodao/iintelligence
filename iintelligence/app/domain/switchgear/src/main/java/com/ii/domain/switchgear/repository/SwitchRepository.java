package com.ii.domain.switchgear.repository;


import com.ii.domain.switchgear.Switch;

/**
 * Created by liyou on 17/4/18.
 */
public interface SwitchRepository {

    void find(Switch device);


    void add(Switch device);


    void upate(Switch device);
}
