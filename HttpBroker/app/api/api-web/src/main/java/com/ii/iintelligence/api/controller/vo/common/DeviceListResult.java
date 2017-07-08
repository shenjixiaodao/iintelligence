package com.ii.iintelligence.api.controller.vo.common;

import com.ii.iintelligence.api.controller.result.WebResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liyou on 17/4/22.
 */
public class DeviceListResult extends WebResult {

    @ApiModelProperty(value = "设备列表")
    private List<DeviceVo> voList;

    public List<DeviceVo> getVoList() {
        return voList;
    }

    public void setVoList(List<DeviceVo> voList) {
        this.voList = voList;
    }

    @Override
    public String toString() {
        return "DeviceListResult{" +
                "voList=" + voList +
                '}';
    }
}
