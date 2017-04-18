package com.ii.biz.AyncContinuation;

import com.ect.common.error.Result;
import com.ii.biz.SwitchState.ISwitchStateService;
import com.ii.domain.continuation.SwitchStateHandler;
import com.ii.domain.switchgear.SwitchState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

/**
 * Created by liyou on 17/4/17.
 */
@Service
public class AyncContinuationService {

    private BlockingQueue<SwitchStateHandler> waitStateChangedQueue;

    @Autowired
    @Qualifier("switchStateCheckingExecutor")
    private AsyncTaskExecutor executor;

    @Autowired
    private ISwitchStateService switchStateService;

    public AyncContinuationService() {
    }

    public void registerStateChangedService(final SwitchStateHandler handler) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                //todo 实现处理逻辑
                /**
                 * 1, 设备请求过来的状态和数据库中记录不一致，说明设备状态发生了转换，修正数据库记录
                 * 2, 状态不变，将该handler放入队列，等待状态改变事件触发
                 */
                SwitchState currentState = handler.getSwitchState();
                SwitchState oldState = null;//todo 查询当前状态
                if(null != currentState && oldState.sameStateAs(currentState)){
                    //todo 将请求放入等待队列， 等待触发
                    waitStateChangedQueue.add(handler);
                } else {
                    switchStateService.reviseState(currentState);
                    //todo 是否需要返回响应结果
                    Result result = null;
                    handler.resultReadyEvent(result);
                }
            }
        });
    }

    public void setWaitStateChangedQueue(BlockingQueue<SwitchStateHandler> waitStateChangedQueue) {
        this.waitStateChangedQueue = waitStateChangedQueue;
    }

}
