package com.ii.iintelligence.api.controller.vo.switchgear;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liyou on 17/5/4.
 */
public class GroupSwitchVo {

    @ApiModelProperty(value = "组ID")
    private String groupId;
    @ApiModelProperty(value = "同组开关", dataType = "com.ii.iintelligence.api.controller.vo.switchgear.SwitchVo")
    private List<SwitchVo> switchVos;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<SwitchVo> getSwitchVos() {
        return switchVos;
    }

    public void setSwitchVos(List<SwitchVo> switchVos) {
        this.switchVos = switchVos;
    }

    @Override
    public String toString() {
        return "GroupSwitchVo{" +
                "GroupId=" + groupId +
                ", switchVos=" + switchVos +
                '}';
    }
}
