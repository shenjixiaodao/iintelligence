package com.ii.biz.AyncContinuation;

import com.ect.common.error.Result;
import com.ii.biz.Switch.ISwitchService;
import com.ii.biz.common.SwitchHandlerHolder;
import com.ii.biz.common.error.ResultAssembler;
import com.ii.domain.handler.SwitchHandler;
import com.ii.domain.service.SwitchHandlerService;
import com.ii.domain.switchgear.Switch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Created by liyou on 17/4/17.
 */
@Service
public class SwitchAyncContinuationService implements SwitchHandlerService{

    @Autowired
    @Qualifier("switchStateCheckingExecutor")
    private AsyncTaskExecutor executor;

    @Autowired
    private ISwitchService switchStateService;

    public SwitchAyncContinuationService() {
    }

    @Override
    public void registerStateChangedHandler(final SwitchHandler handler) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
            /**
             * fixme 考虑实时性问题，是否不需要做持久化存储
             */
            Switch currentSwitch = handler.getSwitch();
            //将请求放入等待队列， 等待触发
            SwitchHandler oldHandler = SwitchHandlerHolder.getHolder().
                    putHandler(currentSwitch.deviceId(), handler);
            if(null != oldHandler){
                //当前已存在等待被处理的 handler
                Switch oldSwitch = oldHandler.getSwitch();
                if(!currentSwitch.state().reconfirmeState(oldSwitch.state(), 1)){
                    /**
                     * 两个handler请求间隔时间太短, 考虑由于并发引起的无序性，要求设备再重发一次确认状态 handler;
                     * 此时存在很小的间隙，阻塞等待设备handler的用户事件subscribe
                     * {@see SwitchStateEventProcessor#switchStateChangedProcessor(SwitchStateChangedEvent)}会取走handler
                     */
                    SwitchHandler currentHandler = SwitchHandlerHolder.getHolder().
                            fetchHandler(currentSwitch.deviceId());
                    if(null != currentHandler) {
                        //响应设备重复 result
                        Result result = new Result(false, null, null);
                        ResultAssembler.makeErrorResult(result, "retry");
                        currentHandler.resultReadyEvent(result);
                    }
                }
            }
            }
        });
    }
}
