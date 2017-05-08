package com.ii.domain.switchgear.handler;

import com.ii.domain.base.handler.Handler;
import com.ii.domain.switchgear.Switch;

/**
 * Created by liyou on 17/4/17.
 */
public interface SwitchHandler<T> extends Handler<T> {

    /**
     * 所有开关状态处理的控制逻辑必须能够获取当前设备状态
     * @return 当前开关的状态
     */
    Switch getSwitch();

}
