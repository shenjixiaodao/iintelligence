package com.ii.data.switchgear.repository;

import com.ii.data.switchgear.mapper.EventMapper;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.GroupId;
import com.ii.domain.base.event.AbstractChangeStatusEvent;
import com.ii.domain.switchgear.event.SwitchChangeStatusEvent;
import com.ii.domain.switchgear.event.SwitchesChangeStatusEvent;
import com.ii.domain.switchgear.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 2017/5/13.
 */
@Repository
public class EventRepositoryImpl implements EventRepository{

    /**
     * 批量查询，一次查询返回记录数
     */
    private final static Integer batchSize = 300;

    @Autowired
    private EventMapper eventMapper;

    @Override
    public void storeStatusEvent(SwitchChangeStatusEvent event) {
        eventMapper.addStatusEvent(event);
    }

    @Override
    public void updateStatusEvent(SwitchChangeStatusEvent event) {
        eventMapper.updateStatusEvent(event);
    }

    @Override
    public SwitchChangeStatusEvent findStatusEvent(DeviceId deviceId) {
        return eventMapper.findStatusEvent(deviceId);
    }

    @Override
    public List<SwitchChangeStatusEvent> findStatusEvent(List<DeviceId> deviceIds) {
        return eventMapper.findStatusEvents(deviceIds);
    }

    @Override
    public List<SwitchChangeStatusEvent> nextPeriodStatusEvent(int batchNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageSize", batchSize);
        map.put("offset", batchSize * batchNo);
        map.put("periodEnabled", AbstractChangeStatusEvent.PeriodEnabled.Yes);
        return eventMapper.findSwitchStatusEvents(map);
    }

    @Override
    public void storeStatusEvent(SwitchesChangeStatusEvent event) {
        eventMapper.addSwitchesStatusEvent(event);
    }

    @Override
    public void updateStatusEvent(SwitchesChangeStatusEvent event) {
        eventMapper.updateSwitchesStatusEvent(event);
    }

    @Override
    public SwitchesChangeStatusEvent findSwitchesStatusEvent(GroupId groupId) {
        return eventMapper.findSwitchesStatusEvent(groupId);
    }

    @Override
    public List<SwitchesChangeStatusEvent> nextPeriodSwitchesStatusEvent(int batchNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageSize", batchSize);
        map.put("offset", batchSize * batchNo);
        map.put("periodEnabled", AbstractChangeStatusEvent.PeriodEnabled.Yes);
        return eventMapper.findSwitchesStatusEvents(map);
    }

    @Override
    public int getBatchSize() {
        return this.batchSize;
    }


}
