package com.ii.iintelligence.api.controller.vo.switchgear;

import com.ii.iintelligence.api.controller.result.WebResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public class SwitchListResult extends WebResult {

    @ApiModelProperty(value = "结果集", dataType = "com.ii.iintelligence.api.controller.vo.switchgear.SwitchVo")
    private List<SwitchVo> voList;

    public List<SwitchVo> getVoList() {
        return voList;
    }

    public void setVoList(List<SwitchVo> voList) {
        this.voList = voList;
    }

    @Override
    public String toString() {
        return "SwitchListResult{" +
                "voList=" + voList +
                '}';
    }
}
