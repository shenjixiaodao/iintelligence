package com.ii.biz.SwitchState;

import com.ii.domain.switchgear.SwitchState;

/**
 * Created by liyou on 17/4/18.
 */
public interface ISwitchStateService {

    /**
     * 修正服务器记录的设备状态
     * @param state 设备传入的当前实际状态
     */
    void reviseState(SwitchState state);

}
