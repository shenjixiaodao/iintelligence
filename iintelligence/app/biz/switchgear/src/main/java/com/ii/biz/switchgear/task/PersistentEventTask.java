package com.ii.biz.switchgear.task;

import com.ii.biz.common.common.event.eventbus.ScheduledEventBus;
import com.ii.domain.switchgear.event.SwitchChangeStatusEvent;
import com.ii.domain.switchgear.event.SwitchesChangeStatusEvent;
import com.ii.domain.switchgear.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by liyou on 2017/5/15.
 */
@Service("persistentEventTask")
public class PersistentEventTask {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ScheduledEventBus eventBus;

    public void periodStatusEvent(){
        int batchNo = 0;
        List<SwitchChangeStatusEvent> events;
        do {
            events = eventRepository.nextPeriodStatusEvent(batchNo);
            for(SwitchChangeStatusEvent event : events){
                if(event.canPost()){
                    eventBus.post(event, event.delay());
                }
            }
        } while (eventRepository.getBatchSize() == events.size());
    }

    public void periodSwitchesStatusEvent(){
        int batchNo = 0;
        List<SwitchesChangeStatusEvent> events;
        do {
            events = eventRepository.nextPeriodSwitchesStatusEvent(batchNo);
            for(SwitchesChangeStatusEvent event : events){
                if(event.canPost()){
                    eventBus.post(event, event.delay());
                }
            }
        } while (eventRepository.getBatchSize() == events.size());
    }

}
