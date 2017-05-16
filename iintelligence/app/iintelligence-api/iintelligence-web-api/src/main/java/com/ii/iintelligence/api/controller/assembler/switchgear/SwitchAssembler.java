package com.ii.iintelligence.api.controller.assembler.switchgear;

import com.ect.common.error.CommonError;
import com.ect.common.error.Result;
import com.ii.domain.base.DeviceId;
import com.ii.domain.base.GroupId;
import com.ii.domain.switchgear.GroupSwitch;
import com.ii.domain.switchgear.GroupsSwitch;
import com.ii.domain.switchgear.Switch;
import com.ii.domain.switchgear.SwitchStatus;
import com.ii.iintelligence.api.controller.constatns.WebConstants;
import com.ii.iintelligence.api.controller.vo.switchgear.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyou on 17/4/21.
 */
public class SwitchAssembler {

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

    public static List<GroupSwitchVo> groupsSwitchToWebResult(GroupsSwitch groups) {
        List<GroupSwitchVo> groupsVo = new ArrayList<>(groups.groups().size());
        for( GroupSwitch group : groups.groups()){
            GroupSwitchVo groupVo = new GroupSwitchVo();
            List<SwitchVo> vos = switchesToVos(group.switches());
            groupVo.setGroupId(group.id().id());
            groupVo.setSwitchVos(vos);
            groupsVo.add(groupVo);
        }
        return groupsVo;
    }

    public static GroupSwitch voToGroupSwitch(GroupSwitchVo groupSwitchVo) {
        GroupId groupId = new GroupId(groupSwitchVo.getGroupId());
        GroupSwitch groupSwitch = new GroupSwitch(groupId, vosToSwitches(groupSwitchVo.getSwitchVos()));
        return groupSwitch;
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
