package com.ii.biz.switchgear.AyncContinuation;

import com.google.common.eventbus.EventBus;
import com.ii.biz.switchgear.common.UserSwitchHandlerHolder;
import com.ii.domain.event.SwitchStatusChangedEvent;
import com.ii.domain.event.ChangeSwitchStatusOKEvent;
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
    public void registerStatusCommandHandler(final UserSwitchHandler handler) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                /**
                 *  用户请求修改设备状态
                 *  1，注册 handler，等待事件 {@link ChangeSwitchStatusOKEvent}；
                 *  2，发布事件 {@link SwitchStatusChangedEvent}
                 */
                Switch currentSwitch = handler.getSwitch();
                UserSwitchHandlerHolder.getHolder().putHandler(currentSwitch.deviceId(), handler);
                SwitchStatusChangedEvent event = new SwitchStatusChangedEvent(currentSwitch);
                eventBus.post(event);
            }
        });
    }
}
