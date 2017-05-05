package com.ii.iintelligence.api.controller.vo.switchgear;

import com.ii.iintelligence.api.controller.result.WebResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liyou on 17/5/4.
 */
public class GroupsSwitchResult extends WebResult {

    @ApiModelProperty(value = "同组开关", dataType = "com.ii.iintelligence.api.controller.vo.switchgear.GroupSwitchVo")
    private List<GroupSwitchVo> groupSwitchVos;

    public List<GroupSwitchVo> getGroupSwitchVos() {
        return groupSwitchVos;
    }

    public void setGroupSwitchVos(List<GroupSwitchVo> groupSwitchVos) {
        this.groupSwitchVos = groupSwitchVos;
    }

    @Override
    public String toString() {
        return "GroupsSwitchResult{" +
                "groupSwitchVos=" + groupSwitchVos +
                '}';
    }
}
