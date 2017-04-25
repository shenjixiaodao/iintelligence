package com.ii.biz.switchgear.service.impl;

import com.google.common.eventbus.EventBus;
import com.ii.biz.switchgear.service.ISwitchService;
import com.ii.domain.event.ChangeSwitchStatusOKEvent;
import com.ii.domain.switchgear.Switch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyou on 17/4/18.
 */
@Service
public class SwitchServiceImpl implements ISwitchService {

    @Autowired
    private EventBus eventBus;

    @Override
    public void reviseStatus(Switch state) {
        //todo
    }

    @Override
    public void switchStatusChangedConfirm(Switch s) {
        ChangeSwitchStatusOKEvent event = new ChangeSwitchStatusOKEvent(s);
        eventBus.post(event);

    }
}
