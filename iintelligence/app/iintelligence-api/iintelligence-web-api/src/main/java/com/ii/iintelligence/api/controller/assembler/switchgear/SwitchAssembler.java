package com.ii.iintelligence.api.controller.assembler.switchgear;

import com.ect.common.error.CommonError;
import com.ect.common.error.Result;
import com.ii.domain.base.DeviceId;
import com.ii.domain.switchgear.Switch;
import com.ii.domain.switchgear.SwitchState;
import com.ii.iintelligence.api.controller.constatns.WebConstants;
import com.ii.iintelligence.api.controller.vo.switchgear.SwitchResult;
import com.ii.iintelligence.api.controller.vo.switchgear.SwitchVo;

/**
 * Created by liyou on 17/4/21.
 */
public class SwitchAssembler {

    public static Switch voToSwitch(SwitchVo vo){
        DeviceId deviceId = new DeviceId(vo.getDeviceId());
        SwitchState state = new SwitchState(SwitchState.State.codeOf(vo.getState()), System.currentTimeMillis());
        return new Switch(deviceId, state);
    }

    public static SwitchResult switchToWebResult(Result<Switch> s) {
        SwitchResult result = new SwitchResult();
        if(s.isSuccess()) {
            result.setResultCode(WebConstants.RESULT_SUCCESS_CODE);
            SwitchVo vo = switchToVo(s.getResultObj());
            result.setsVo(vo);
        }else {
            result.setResultCode(WebConstants.RESULT_FAIL_CODE);
            CommonError error = s.getErrorContext().fetchCurrentError();
            result.setErrorCode(error.getErrorCode().toString());
            result.setMessage(error.getErrorMsg());
        }
        return result;
    }

    public static SwitchVo switchToVo(Switch s) {
        SwitchVo vo = new SwitchVo();
        vo.setDeviceId(s.deviceId().toString());
        vo.setState(s.state().toString());
        return vo;
    }

}
