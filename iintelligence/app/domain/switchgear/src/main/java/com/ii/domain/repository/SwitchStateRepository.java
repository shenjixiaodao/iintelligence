package com.ii.domain.repository;

import com.ii.domain.switchgear.SwitchState;

/**
 * Created by liyou on 17/4/18.
 */
public interface SwitchStateRepository {

    void find(SwitchState state);

    void add(SwitchState state);

    void upate(SwitchState state);


}
