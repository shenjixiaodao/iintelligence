package com.ii.iintelligence.api.controller.vo.switchgear;


import com.ii.iintelligence.api.controller.result.WebResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liyou on 17/4/18.
 */
public class SwitchEventResult extends WebResult {

    @ApiModelProperty(value = "结果集", dataType = "com.ii.iintelligence.api.controller.vo.switchgear.SwitchEventVo")
    private SwitchEventVo eventVo;

    public SwitchEventVo getEventVo() {
        return eventVo;
    }

    public void setEventVo(SwitchEventVo eventVo) {
        this.eventVo = eventVo;
    }

    @Override
    public String toString() {
        return "SwitchEventResult{" +
                "eventVo=" + eventVo +
                '}';
    }
}
