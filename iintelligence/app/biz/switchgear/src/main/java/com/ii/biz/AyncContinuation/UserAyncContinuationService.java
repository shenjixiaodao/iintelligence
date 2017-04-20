package com.ii.biz.AyncContinuation;

import com.google.common.eventbus.EventBus;
import com.ii.biz.common.UserSwitchHandlerHolder;
import com.ii.domain.event.SwitchStateChangedEvent;
import com.ii.domain.event.ChangeSwitchStateOKEvent;
import com.ii.domain.handler.UserSwitchHandler;
import com.ii.domain.service.UserSwitchHandlerService;
import com.ii.domain.switchgear.Switch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Created by liyou on 17/4/17.
 */
@Service
public class UserAyncContinuationService implements UserSwitchHandlerService {

    @Autowired
    @Qualifier("switchStateCheckingExecutor")
    private AsyncTaskExecutor executor;

    @Autowired
    private EventBus eventBus;

    public UserAyncContinuationService() {
    }

    @Override
    public void registerStateCommandHandler(final UserSwitchHandler handler) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                /**
                 *  用户请求修改设备状态
                 *  1，注册 handler，等待事件 {@link ChangeSwitchStateOKEvent}；
                 *  2，发布事件 {@link SwitchStateChangedEvent}
                 */
                Switch currentSwitch = handler.getSwitch();
                UserSwitchHandlerHolder.getHolder().putHandler(currentSwitch.deviceId(), handler);
                SwitchStateChangedEvent event = new SwitchStateChangedEvent(currentSwitch);
                eventBus.post(event);
            }
        });
    }
}
