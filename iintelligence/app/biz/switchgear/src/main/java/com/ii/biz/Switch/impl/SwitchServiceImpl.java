package com.ii.biz.Switch.impl;

import com.google.common.eventbus.EventBus;
import com.ii.biz.Switch.ISwitchService;
import com.ii.domain.event.ChangeSwitchStateOKEvent;
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
    public void reviseState(Switch state) {
        //todo
    }

    @Override
    public void switchStateChangedConfirm(Switch s) {
        ChangeSwitchStateOKEvent event = new ChangeSwitchStateOKEvent(s);
        eventBus.post(event);

    }
}
