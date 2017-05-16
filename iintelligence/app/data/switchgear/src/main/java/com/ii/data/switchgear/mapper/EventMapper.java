package com.ii.data.switchgear.mapper;

import com.ii.domain.base.DeviceId;
import com.ii.domain.base.GroupId;
import com.ii.domain.switchgear.event.SwitchChangeStatusEvent;
import com.ii.domain.switchgear.event.SwitchesChangeStatusEvent;

import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 2017/5/13.
 */
public interface EventMapper {

    void addStatusEvent(SwitchChangeStatusEvent event);
    void updateStatusEvent(SwitchChangeStatusEvent event);
    SwitchChangeStatusEvent findStatusEvent(DeviceId deviceId);
    List<SwitchChangeStatusEvent> findStatusEvents(List<DeviceId> deviceIds);

    void addSwitchesStatusEvent(SwitchesChangeStatusEvent event);
    void updateSwitchesStatusEvent(SwitchesChangeStatusEvent event);
    SwitchesChangeStatusEvent findSwitchesStatusEvent(GroupId groupId);

    List<SwitchesChangeStatusEvent> findSwitchesStatusEvents(Map map);
    int countSwitchesStatusEvent();

    List<SwitchChangeStatusEvent> findSwitchStatusEvents(Map map);
    int countSwitchStatusEvent();
}
