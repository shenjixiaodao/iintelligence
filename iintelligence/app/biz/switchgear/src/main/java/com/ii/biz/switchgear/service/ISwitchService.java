package com.ii.biz.switchgear.service;

import com.ii.domain.switchgear.Switch;

/**
 * Created by liyou on 17/4/18.
 */
public interface ISwitchService{

    /**
     * 修正服务器记录的设备状态
     * @param s 设备传入的当前实际状态
     */
    void reviseStatus(Switch s);

    /**
     * 注册设备: 包括激活设备、信息注册等
     * @param s
     */
    void registerSwitch(Switch s);

}
