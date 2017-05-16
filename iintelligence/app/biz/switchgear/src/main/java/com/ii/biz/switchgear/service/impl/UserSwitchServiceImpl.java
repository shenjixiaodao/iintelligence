package com.ii.biz.switchgear.service.impl;

import com.ii.biz.common.common.event.eventbus.ScheduledEventBus;
import com.ii.biz.switchgear.common.SwitchHandlerHolder;
import com.ii.biz.switchgear.service.IUserSwitchService;
import com.ii.biz.user.service.IUserDeviceService;
import com.ii.domain.base.Device;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.DeviceType;
import com.ii.domain.base.handler.Handler;
import com.ii.domain.switchgear.Switch;
import com.ii.domain.switchgear.event.SwitchChangeStatusEvent;
import com.ii.domain.switchgear.event.SwitchesChangeStatusEvent;
import com.ii.domain.switchgear.handler.SwitchHandler;
import com.ii.domain.switchgear.handler.SwitchesHandler;
import com.ii.domain.switchgear.GroupsSwitch;
import com.ii.domain.switchgear.repository.EventRepository;
import com.ii.domain.switchgear.service.UserSwitchScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by liyou on 17/4/28.
 */
@Service
public class UserSwitchServiceImpl implements IUserSwitchService, UserSwitchScheduledService {

    @Autowired
    private IUserDeviceService userDeviceService;

    @Autowired
    @Qualifier("scheduledEventBus")
    private ScheduledEventBus eventBus;

    @Autowired
    private EventRepository eventRepository;

    /**
     * 返回由{@link SwitchHandler#getSwitch()}和{@link SwitchesHandler#getSwitches()}的返回结果进行分组的分组开关。
     * @param uid
     * @return
     */
    @Override
    public GroupsSwitch findGroupSwitch(String uid) {
        if(StringUtils.isEmpty(uid))
            throw new IllegalArgumentException("uid不能为空");
        /**
         * 获取用户所有{@link com.ii.domain.base.Device.BindingStatus.Binding}的开关设备
         */

        List<Device> devices = userDeviceService.findBindingDevice(uid, DeviceType.Switch);
        /**
         * 获取所有的handler实例, 对handler持有的switch进行分组
         */
        GroupsSwitch groupSwitch = new GroupsSwitch();
        Map<Handler, Boolean> handlers = new HashMap<>();//过滤重复
        for(Device device : devices){
            Handler handler = SwitchHandlerHolder.getHolder().getHandler(device.deviceId());
            if (handler != null)
                handlers.put(handler, Boolean.TRUE);
        }
        for(Map.Entry<Handler,Boolean> entry: handlers.entrySet()){
            Handler handler = entry.getKey();
            if(handler instanceof SwitchHandler){
                groupSwitch.putIntoNewGroup(((SwitchHandler) handler).getSwitch());
            } else if(handler instanceof SwitchesHandler) {
                groupSwitch.putIntoNewGroup(((SwitchesHandler) handler).getSwitches());
            }
        }
        return groupSwitch;
    }

    @Override
    public void postDelayChangeStatusEvent(SwitchChangeStatusEvent event) {
        if(!event.isPersistable()){
            eventBus.post(event, event.delay());
        }else {
            //todo 持久化事件
        }
    }

    @Override
    @Transactional
    public void postPeriodChangeStatusEvent(SwitchChangeStatusEvent event) {
        if(event.canPost()){
            eventBus.post(event, event.delay());
        }
        if(eventRepository.findStatusEvent(event.s().deviceId()) == null) {
            eventRepository.storeStatusEvent(event);
        } else {
            eventRepository.updateStatusEvent(event);
        }
    }

    @Override
    @Transactional
    public void postPeriodChangeStatusEvent(SwitchesChangeStatusEvent switchesEvent) {
        if(switchesEvent.canPost()){
            eventBus.post(switchesEvent, switchesEvent.delay());
        }
        /**
         * 首先检查{@param SwitchesEvent}中关联的设备是否已有过事件记录
         */
        List<DeviceId> deviceIds = new ArrayList<>(switchesEvent.groupSwitch().switches().size());
        for(Switch s : switchesEvent.groupSwitch().switches()){
            deviceIds.add(s.deviceId());
        }
        List<SwitchChangeStatusEvent> oldEvents = eventRepository.findStatusEvent(deviceIds);
        if(oldEvents.size() > 0) {
            //找到已存在的设备关联的事件记录
            List<Switch> oldSwitches = new ArrayList<>(oldEvents.size());
            for (SwitchChangeStatusEvent oldEvent : oldEvents) {
                oldSwitches.add(oldEvent.s());
            }
            //把当前事件中关联的设备移除
            for (Iterator<Switch> iter = switchesEvent.groupSwitch().switches().iterator(); iter.hasNext(); ) {
                if (oldSwitches.contains(iter.next()))
                    iter.remove();
            }
            //更新已存在的设备事件信息
            SwitchesChangeStatusEvent oldSwitchesEvents = switchesEvent.copyWithoutSwitches();
            oldSwitchesEvents.groupSwitch().switches(oldSwitches);
            eventRepository.updateStatusEvent(oldSwitchesEvents);
        }
        eventRepository.storeStatusEvent(switchesEvent);
    }
}
