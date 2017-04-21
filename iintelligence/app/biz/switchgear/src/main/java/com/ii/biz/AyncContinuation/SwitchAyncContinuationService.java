package com.ii.biz.AyncContinuation;

import com.ect.common.error.Result;
import com.ii.biz.Switch.ISwitchService;
import com.ii.biz.common.SwitchHandlerHolder;
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
            /** fixme 考虑实时性问题，是否不需要做持久化存储
             * 1, 设备请求过来的状态和数据库中记录不一致，说明设备状态发生了转换，修正数据库记录
             * 2, 状态不变，将该handler放入队列，等待状态改变事件触发
             */
            Switch currentSwitch = handler.getSwitch();
            Switch oldSwitch = null;//todo 查询当前状态
            if(null != currentSwitch && oldSwitch.sameStateAs(currentSwitch)) {
                //todo 将请求放入等待队列， 等待触发
                SwitchHandler oldHandler = SwitchHandlerHolder.getHolder().
                        putHandler(currentSwitch.deviceId(), handler);
                if(null != oldHandler){
                    //todo 当前已存在等待被处理的 handler
                    oldSwitch = oldHandler.getSwitch();
                    if(!currentSwitch.state().reconfirmeState(oldSwitch.state(), 1)){
                        //fixme 两个handler请求间隔时间太短, 考虑由于并发引起的无序性，要求设备再重发一次确认状态 handler
                        SwitchHandler currentHandler = SwitchHandlerHolder.getHolder().
                                fetchHandler(currentSwitch.deviceId());
                        Result result = null;//fixme 重发请求result
                        currentHandler.resultReadyEvent(result);
                    }
                }
            } else {
                switchStateService.reviseState(currentSwitch);
                //todo 是否需要返回响应结果?
                Result result = null;
                handler.resultReadyEvent(result);
            }
            }
        });
    }
}
