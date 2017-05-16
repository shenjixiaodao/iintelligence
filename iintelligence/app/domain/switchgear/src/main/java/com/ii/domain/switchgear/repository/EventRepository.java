package com.ii.domain.switchgear.repository;

import com.ii.domain.base.DeviceId;
import com.ii.domain.base.GroupId;
import com.ii.domain.switchgear.event.SwitchChangeStatusEvent;
import com.ii.domain.switchgear.event.SwitchesChangeStatusEvent;

import java.util.List;

/**
 * Created by liyou on 2017/5/13.
 */
public interface EventRepository {

    void storeStatusEvent(SwitchChangeStatusEvent event);
    void updateStatusEvent(SwitchChangeStatusEvent event);
    SwitchChangeStatusEvent findStatusEvent(DeviceId deviceId);
    List<SwitchChangeStatusEvent> findStatusEvent(List<DeviceId> deviceIds);

    /**
     * 从持久化数据中获取指定批次的周期事件, 如果要获取连续批次的数据, 则应当将上一批次{@param batchNo}做加一后传入
     * @param batchNo 从0开始计数
     * @return 返回当前批次分组事件实例
     */
    List<SwitchChangeStatusEvent> nextPeriodStatusEvent(int batchNo);

    void storeStatusEvent(SwitchesChangeStatusEvent event);
    void updateStatusEvent(SwitchesChangeStatusEvent event);
    SwitchesChangeStatusEvent findSwitchesStatusEvent(GroupId groupId);

    /**
     * 从持久化数据中获取指定批次的分组周期事件, 如果要获取连续批次的数据, 则应当将上一批次{@param batchNo}做加一后传入
     * @param batchNo 从0开始计数
     * @return 返回当前批次分组周期事件实例
     */
    List<SwitchesChangeStatusEvent> nextPeriodSwitchesStatusEvent(int batchNo);

    /**
     * 返回每批次记录数
     * @return
     */
    int getBatchSize();
}
