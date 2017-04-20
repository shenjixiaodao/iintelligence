package com.ii.biz.Switch;

import com.ii.domain.switchgear.Switch;

/**
 * Created by liyou on 17/4/18.
 */
public interface ISwitchService {

    /**
     * 修正服务器记录的设备状态
     * @param s 设备传入的当前实际状态
     */
    void reviseState(Switch s);

    /**
     * 设备状态修改确认
     * @param s 确认设备
     */
    void switchStateChangedConfirm(Switch s);

}
