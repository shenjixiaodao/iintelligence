package com.ii.biz.event.processor;

import com.google.common.eventbus.Subscribe;
import com.ii.domain.event.SwitchStateChangedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by liyou on 17/4/18.
 */
@Component
public class SwitchStateEventProcessor {

    @Subscribe
    public void SwitchStateChangedProcessor(SwitchStateChangedEvent event){
        //todo 处理事件
    }

}
