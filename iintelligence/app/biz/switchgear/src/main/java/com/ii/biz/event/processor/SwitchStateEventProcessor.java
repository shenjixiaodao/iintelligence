package com.ii.biz.event.processor;

import com.ect.common.error.Result;
import com.google.common.eventbus.Subscribe;
import com.ii.biz.common.SwitchHandlerHolder;
import com.ii.biz.common.UserSwitchHandlerHolder;
import com.ii.domain.event.ChangeSwitchStateOKEvent;
import com.ii.domain.event.SwitchStateChangedEvent;
import com.ii.domain.handler.SwitchHandler;
import com.ii.domain.handler.UserSwitchHandler;
import com.ii.domain.switchgear.Switch;
import org.springframework.stereotype.Component;

/**
 * Created by liyou on 17/4/18.
 */
@Component
public class SwitchStateEventProcessor {

    @Subscribe
    public void switchStateChangedProcessor(SwitchStateChangedEvent event){
        Switch currentSwitch = event.getSwitch();
        /**
         * 如果返回 handler 为 null， 即此时没有 handler 响应该 event，考虑到响应结果的实时性，
         * 则该 event 应当在十秒内被处理掉。
         */
        SwitchHandler handler = SwitchHandlerHolder.getHolder().fetchHandler(currentSwitch.deviceId());
        Result<Switch> result = new Result<>(false, null, null);
        if(null != handler) {
            /**
             *
             * 1, 如果 currentSwitch 和 oldSwitch状态不一致, 则向设备响应结果
             * 2, 如果 currentSwitch 和 oldSwitch状态一致, 则忽略(fixme)
             */
            Switch oldSwitch = handler.getSwitch();
            if(!currentSwitch.sameStateAs(oldSwitch)) {
                result.setSuccess(true);
                result.setResultObj(currentSwitch);
                handler.resultReadyEvent(result);
            }
        } else {
            UserSwitchHandler userHandler = UserSwitchHandlerHolder.getHolder().fetchHandler(currentSwitch.deviceId());
            result.setSuccess(false);
            //todo 没有可处理该even的 handler, 设置响应用户异常结果
            //result.setErrorContext();
            userHandler.resultReadyEvent(result);
        }
    }

    @Subscribe
    public void changeSwitchStateOKProcessor(ChangeSwitchStateOKEvent event){
        Switch currentSwitch = event.getSwitch();
        UserSwitchHandler handler = UserSwitchHandlerHolder.getHolder().fetchHandler(currentSwitch.deviceId());
        Result<Switch> result = new Result<>(false, null, null);
        if(null != handler){
            //将当前设备响应给用户
            //result.setResultObj(currentSwitch);
            result.setSuccess(true);
            handler.resultReadyEvent(result);
        }
    }

}
