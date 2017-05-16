package com.ii.data.switchgear.test;

import com.ii.data.switchgear.mapper.EventMapper;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.GroupId;
import com.ii.domain.base.event.AbstractChangeStatusEvent;
import com.ii.domain.switchgear.GroupSwitch;
import com.ii.domain.switchgear.Switch;
import com.ii.domain.switchgear.SwitchStatus;
import com.ii.domain.switchgear.event.SwitchChangeStatusEvent;
import com.ii.domain.switchgear.event.SwitchesChangeStatusEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 2017/5/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config-context.xml", "classpath*:META-INF/spring/*.xml"})
public class SwitchTest {

    @Autowired
    private EventMapper eventMapper;

    @Test
    public void addSwitchesStatusEvent(){
        List<Switch> switches = new ArrayList<>();
        Switch s = new Switch(new DeviceId("1234"), new SwitchStatus(SwitchStatus.Status.Off, System.currentTimeMillis()));
        switches.add(s);
        s = new Switch(new DeviceId("1235"), new SwitchStatus(SwitchStatus.Status.On, System.currentTimeMillis()));
        switches.add(s);
        GroupSwitch groupSwitch =  new GroupSwitch(new GroupId("123"),switches);
        SwitchesChangeStatusEvent event = new SwitchesChangeStatusEvent(groupSwitch, "0 0 8 * * ?");
        eventMapper.addSwitchesStatusEvent(event);
        System.out.println();
    }

    @Test
    public void updateSwitchesStatusEvent(){
        List<Switch> switches = new ArrayList<>();
        Switch s = new Switch(new DeviceId("1234"), new SwitchStatus(SwitchStatus.Status.Off, System.currentTimeMillis()));
        switches.add(s);
        s = new Switch(new DeviceId("1235"), new SwitchStatus(SwitchStatus.Status.On, System.currentTimeMillis()));
        switches.add(s);
        GroupSwitch groupSwitch =  new GroupSwitch(new GroupId("123"),switches);
        SwitchesChangeStatusEvent event = new SwitchesChangeStatusEvent(groupSwitch, "0 0 8 * * ?");
        event.periodEnabled(AbstractChangeStatusEvent.PeriodEnabled.Yes);
        eventMapper.updateSwitchesStatusEvent(event);
        System.out.println();
    }

    @Test
    public void findSwitchesStatusEvent(){
        GroupId groupId = new GroupId("123");
        SwitchesChangeStatusEvent event = eventMapper.findSwitchesStatusEvent(groupId);
        System.out.println();
    }

    @Test
    public void addStatusEvent(){
        Switch s = new Switch(new DeviceId("1233"), new SwitchStatus(SwitchStatus.Status.Off, System.currentTimeMillis()));
        SwitchChangeStatusEvent event = new SwitchChangeStatusEvent(s, "0 0 8 * * ?");
        eventMapper.addStatusEvent(event);
        System.out.println();
    }

    @Test
    public void updateStatusEvent(){
        Switch s = new Switch(new DeviceId("1233"), new SwitchStatus(SwitchStatus.Status.On, System.currentTimeMillis()));
        SwitchChangeStatusEvent event = new SwitchChangeStatusEvent(s, "0 0 8 * * ?");
        event.periodEnabled(AbstractChangeStatusEvent.PeriodEnabled.Yes);
        eventMapper.updateStatusEvent(event);
        System.out.println();
    }

    @Test
    public void findStatusEvent(){
        DeviceId deviceId = new DeviceId("1233");
        SwitchChangeStatusEvent event = eventMapper.findStatusEvent(deviceId);
        System.out.println();
    }

    @Test
    public void findSwitchesStatusEvents(){
        Map<String, String> map = new HashMap<>();
        map.put("pageSize","5");
        map.put("offset","0");
        int count = eventMapper.countSwitchesStatusEvent();
        List<SwitchesChangeStatusEvent> events = eventMapper.findSwitchesStatusEvents(map);
        System.out.println();
    }

    @Test
    public void findSwitchStatusEvents(){
        Map<String, String> map = new HashMap<>();
        map.put("pageSize","5");
        map.put("offset","0");
        int count = eventMapper.countSwitchStatusEvent();
        List<SwitchChangeStatusEvent> events = eventMapper.findSwitchStatusEvents(map);
        System.out.println();
    }
}
