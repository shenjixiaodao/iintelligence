package com.ii.iintelligence.api.controller.vo.switchgear;

import com.ii.iintelligence.api.controller.result.WebResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public class SwitchResult extends WebResult {

    @ApiModelProperty(value = "结果集", dataType = "com.ii.iintelligence.api.controller.vo.switchgear.SwitchVo")
    private SwitchVo sVo;

    public SwitchVo getsVo() {
        return sVo;
    }

    public void setsVo(SwitchVo sVo) {
        this.sVo = sVo;
    }

    @Override
    public String toString() {
        return "SwitchResult{" +
                "sVo=" + sVo +
                '}';
    }
}
