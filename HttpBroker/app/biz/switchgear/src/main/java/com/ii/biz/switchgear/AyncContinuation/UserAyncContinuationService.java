package com.ii.biz.switchgear.AyncContinuation;

import com.google.common.eventbus.EventBus;
import com.ii.biz.switchgear.common.UserSwitchHandlerHolder;
import com.ii.domain.switchgear.event.SwitchEvent;
import com.ii.domain.switchgear.handler.UserSwitchEventHandler;
import com.ii.domain.switchgear.service.UserSwitchHandlerService;
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
    public void registerStatusEventHandler(final UserSwitchEventHandler handler) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                /**
                 *  用户请求修改设备状态
                 *  1，注册 handler，等待事件 {@link SwitchOKEvent}；
                 *  2，发布事件 {@link SwitchEvent}
                 */
                SwitchEvent event = handler.getEvent();
                UserSwitchHandlerHolder.getHolder().putHandler(event.s().deviceId(), handler);
                //eventBus.post(event);
            }
        });
    }

}
