package com.ii.domain.switchgear.service;

import com.ii.domain.switchgear.event.SwitchChangeStatusEvent;

/**
 * Created by liyou on 2017/5/13.
 */
public interface UserSwitchScheduledService {

    /**
     * 发布一个只执行一次的延迟发生事件
     * @param event
     */
    void postDelayStatusChangedEvent(SwitchChangeStatusEvent event);

    /**
     * 发布一个周期发生的事件：
     * 1，如果该事件如果很短时间内需要发布一次，则发布该事件；
     * 2，持久化存储该事件。
     * 持久化时可能存在以下几种情况:
     * 1, 该设备之前可能以组设备形式发布过周期状态事件;
     * 2, 该设备之前未发布周期状态事件.
     * @param event
     */
    void postPeriodStatusChangedEvent(SwitchChangeStatusEvent event);


}
