package com.ii.iintelligence.api.controller.vo.switchgear.delay;

import com.ii.iintelligence.api.controller.vo.switchgear.SwitchEventVo;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liyou on 2017/6/4.
 */
public class SwitchDelayEventVo extends SwitchEventVo {

    @ApiModelProperty(value = "事件发布延时")
    private Integer delay;
}
