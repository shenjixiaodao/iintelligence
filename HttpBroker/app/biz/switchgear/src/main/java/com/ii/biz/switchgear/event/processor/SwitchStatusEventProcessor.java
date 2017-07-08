package com.ii.biz.switchgear.event.processor;


import com.google.common.eventbus.Subscribe;
import com.ii.domain.switchgear.event.SwitchEvent;
import org.springframework.stereotype.Component;

/**
 * Created by liyou on 17/4/18.
 */
@Component
public class SwitchStatusEventProcessor {

    @Subscribe
    public void switchEventProcessor(SwitchEvent event){

    }

}
