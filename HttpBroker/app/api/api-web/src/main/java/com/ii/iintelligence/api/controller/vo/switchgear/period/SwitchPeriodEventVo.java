package com.ii.iintelligence.api.controller.vo.switchgear.period;

import com.ii.iintelligence.api.controller.vo.switchgear.SwitchEventVo;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liyou on 2017/6/4.
 */
public class SwitchPeriodEventVo extends SwitchEventVo {

    @ApiModelProperty(value = "事件发布的cron表达式")
    private String cronExpression;
}
