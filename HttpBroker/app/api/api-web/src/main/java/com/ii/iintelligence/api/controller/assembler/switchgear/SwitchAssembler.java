package com.ii.iintelligence.api.controller.assembler.switchgear;

import com.ect.common.error.CommonError;
import com.ect.common.error.Result;
import com.ii.domain.base.DeviceId;
import com.ii.domain.switchgear.Switch;
import com.ii.domain.switchgear.SwitchStatus;
import com.ii.domain.switchgear.event.SwitchEvent;
import com.ii.iintelligence.api.controller.constatns.WebConstants;
import com.ii.iintelligence.api.controller.vo.switchgear.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyou on 17/4/21.
 */
public class SwitchAssembler {

    public static SwitchEvent toSwitchEvent(SwitchEventVo vo){
        DeviceId deviceId = new DeviceId(vo.getSwitchVo().getDeviceId());
        SwitchStatus status = new SwitchStatus(SwitchStatus.Status.codeOf(vo.getSwitchVo().getStatus()),
                System.currentTimeMillis());
        Switch s =  new Switch(deviceId, status);
        SwitchEvent event = new SwitchEvent(s);
        if(!StringUtils.isEmpty(vo.getDeliveryStatus()))
            event.deliveryStatus(vo.getDeliveryStatus());
        return event;
    }

    public static SwitchEventResult toSwitchEventResult(Result<SwitchEvent> s){
        SwitchEventResult result = new SwitchEventResult();
        if(s.isSuccess()) {
            result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
            SwitchVo vo = switchToVo(s.getResultObj().s());
            SwitchEventVo eventVo = new SwitchEventVo();
            eventVo.setSwitchVo(vo);
            eventVo.setDeliveryStatus(s.getResultObj().deliveryStatus().toString());
            result.setEventVo(eventVo);
        }else {
            result.setResultCode(WebConstants.RESULT_FAIL_CODE);
            CommonError error = s.getErrorContext().fetchCurrentError();
            result.setErrorCode(error.getErrorCode().toString());
            result.setMessage(error.getErrorMsg());
        }
        return result;
    }

    public static Switch voToSwitch(SwitchVo vo){
        DeviceId deviceId = new DeviceId(vo.getDeviceId());
        SwitchStatus status = new SwitchStatus(SwitchStatus.Status.codeOf(vo.getStatus()), System.currentTimeMillis());
        return new Switch(deviceId, status);
    }

    public static List<Switch> vosToSwitches(List<SwitchVo> vos){
        List<Switch> switches = new ArrayList<>(vos.size());
        for(SwitchVo vo:vos){
            switches.add(voToSwitch(vo));
        }
        return switches;
    }

    public static SwitchListResult switchesToWebListResult(Result<List<Switch>> switches) {
        SwitchListResult switchListResult = new SwitchListResult();
        if(switches.isSuccess()) {
            switchListResult.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
            List<SwitchVo> vos = switchesToVos(switches.getResultObj());
            switchListResult.setVoList(vos);
        }else {
            switchListResult.setResultCode(WebConstants.RESULT_FAIL_CODE);
            CommonError error = switches.getErrorContext().fetchCurrentError();
            switchListResult.setErrorCode(error.getErrorCode().toString());
            switchListResult.setMessage(error.getErrorMsg());
        }
        return switchListResult;
    }

    public static SwitchVo switchToVo(Switch s) {
        SwitchVo vo = new SwitchVo();
        vo.setDeviceId(s.deviceId().id());
        vo.setStatus(s.status().status().toString());
        return vo;
    }

    public static List<SwitchVo> switchesToVos(List<Switch> switches) {
        List<SwitchVo> vos = new ArrayList<>(switches.size());
        for(Switch s : switches){
            vos.add(switchToVo(s));
        }
        return vos;
    }

}
