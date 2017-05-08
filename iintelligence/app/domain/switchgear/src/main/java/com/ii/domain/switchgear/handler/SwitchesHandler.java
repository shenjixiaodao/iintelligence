package com.ii.domain.switchgear.handler;

import com.ect.common.error.Result;
import com.ii.domain.base.handler.Handler;
import com.ii.domain.switchgear.Switch;

import java.util.List;

/**
 * Created by liyou on 17/4/17.
 */
public interface SwitchesHandler<T> extends Handler<T> {

    /**
     * 所有开关状态处理的控制逻辑必须能够获取当前设备状态,
     * 该handler持有一组开关设备
     * @return 当前开关的状态
     */
    List<Switch> getSwitches();

    /**
     * 对于一组开关关联的一个handler，如果个别或几个开关接收了用户发出的指令，则该handler对应的connection已经结束，
     * 但是未接收到用户指令的开关仍然保持有该handler的引用，所以对于分组或批量开关，在{@link Handler#resultReadyEvent(Result)}前必须检查
     * 该handler是否被处理过。如果为true，则不再响应结果； 否则响应结果。
     * @return true: 已处理， false: 未处理
     */
    boolean isHandled();
}
