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
        if(null != handler) {
            /**
             *
             * 1, 如果 currentSwitch 和 oldSwitch状态不一致, 则向设备响应结果
             * 2, 如果 currentSwitch 和 oldSwitch状态一致, 则忽略(fixme)
             */
            Switch oldSwitch = handler.getSwitch();
            if(!currentSwitch.sameStateAs(oldSwitch)) {
                //todo 处理响应结果
                Result result = null;
                handler.resultReadyEvent(result);
            }
        } else {
            //todo 没有可处理该even的 handler, 设置响应用户结果
        }
    }

    @Subscribe
    public void changeSwitchStateOKProcessor(ChangeSwitchStateOKEvent event){
        Switch currentSwitch = event.getSwitch();
        UserSwitchHandler handler = UserSwitchHandlerHolder.getHolder().fetchHandler(currentSwitch.deviceId());
        if(null != handler){
            //将当前设备响应给用户
        }
    }

}
