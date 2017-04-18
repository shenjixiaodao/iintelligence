package com.ii.domain.continuation;

import com.ii.domain.switchgear.SwitchState;

/**
 * Created by liyou on 17/4/17.
 */
public interface SwitchStateHandler extends ContinuationHandler {

    /**
     * 所有开关状态处理的控制逻辑必须能够获取当前设备状态
     * @return 当前开关的状态
     */
    SwitchState getSwitchState();

}
