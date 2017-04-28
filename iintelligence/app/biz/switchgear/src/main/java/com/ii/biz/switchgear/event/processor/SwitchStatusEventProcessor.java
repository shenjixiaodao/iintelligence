package com.ii.biz.switchgear.event.processor;

import com.ect.common.error.Result;
import com.google.common.eventbus.Subscribe;
import com.ii.biz.switchgear.common.SwitchHandlerHolder;
import com.ii.biz.switchgear.common.UserSwitchHandlerHolder;
import com.ii.biz.common.common.error.ResultAssembler;
import com.ii.domain.event.ChangeSwitchStatusOKEvent;
import com.ii.domain.event.ChangeSwitchesStatusOKEvent;
import com.ii.domain.event.SwitchStatusChangedEvent;
import com.ii.domain.event.SwitchesStatusChangedEvent;
import com.ii.domain.handler.SwitchHandler;
import com.ii.domain.handler.Handler;
import com.ii.domain.handler.SwitchesHandler;
import com.ii.domain.switchgear.Switch;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liyou on 17/4/18.
 */
@Component
public class SwitchStatusEventProcessor {

    @Subscribe
    public void switchStatusChangedProcessor(SwitchStatusChangedEvent event){
        Switch currentSwitch = event.getSwitch();
        /**
         * 如果返回 handler 为 null， 即此时没有 handler 响应该 event，考虑到响应结果的实时性，
         * 则该 event 应当在十秒内被处理掉。
         */
        SwitchHandler handler = (SwitchHandler) SwitchHandlerHolder.getHolder().fetchHandler(currentSwitch.deviceId());
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
            Handler userHandler = UserSwitchHandlerHolder.getHolder().fetchHandler(currentSwitch.deviceId());
            ResultAssembler.makeErrorResult(result, "device_exception");
            //没有可处理该even的 handler, 需要检查设备和系统的连接状态
            //result.setErrorContext();
            userHandler.resultReadyEvent(result);
        }
    }

    /**
     * 一组开关设备状态改变事件处理
     * @param event
     */
    @Subscribe
    public void switchesStatusChangedProcessor(SwitchesStatusChangedEvent event){
        List<Switch> currentSwitches = event.getSwitches();
        /**
         * 如果返回 handler 为 null， 即此时没有 handler 响应该 event，考虑到响应结果的实时性，
         * 则该 event 应当在十秒内被处理掉。
         */
        for(Switch currentSwitch : currentSwitches) {
            //Switch currentSwitch = currentSwitches.get(0);
            SwitchesHandler handler = (SwitchesHandler) SwitchHandlerHolder.getHolder().
                    fetchHandler(currentSwitch.deviceId());
            Result<List<Switch>> result = new Result<>(false, null, null);
            if (null != handler) {
                /**
                 * 将用户发起的所有设备当前状态响应给设备, 此时无论{@param event}中携带的开关设备是否关联该
                 * handler，都在同一个handler里一并响应给设备。
                 */
                result.setSuccess(true);
                result.setResultObj(currentSwitches);
                handler.resultReadyEvent(result);
            } else {
                //没有可处理该even的 handler, 需要检查设备和系统的连接状态, 直接响应用户handler
                Handler userHandler = UserSwitchHandlerHolder.getHolder().fetchHandler(currentSwitch.deviceId());
                ResultAssembler.makeErrorResult(result, "device_exception");
                //result.setErrorContext();
                userHandler.resultReadyEvent(result);
            }
        }
    }

    @Subscribe
    public void changeSwitchStatusOKProcessor(ChangeSwitchStatusOKEvent event){
        Switch currentSwitch = event.getSwitch();
        Handler handler = UserSwitchHandlerHolder.getHolder().fetchHandler(currentSwitch.deviceId());
        Result<Switch> result = new Result<>(false, null, null);
        if(null != handler){
            //将当前设备响应给用户
            result.setResultObj(currentSwitch);
            result.setSuccess(true);
            handler.resultReadyEvent(result);
        }
    }

    @Subscribe
    public void changeSwitchesStatusOKProcessor(ChangeSwitchesStatusOKEvent event){
        List<Switch> currentSwitches = event.getSwitches();
        for(Switch currentSwitch : currentSwitches) {
            Handler handler = UserSwitchHandlerHolder.getHolder().fetchHandler(currentSwitch.deviceId());
            Result<List<Switch>> result = new Result<>(false, null, null);
            if (null != handler) {
                //将当前操作的多台设备结果响应给用户
                result.setResultObj(currentSwitches);
                result.setSuccess(true);
                handler.resultReadyEvent(result);
            }
        }
    }

}
