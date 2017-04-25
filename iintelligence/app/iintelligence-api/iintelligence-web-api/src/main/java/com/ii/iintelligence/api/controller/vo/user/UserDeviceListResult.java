package com.ii.iintelligence.api.controller.vo.user;

import com.ii.iintelligence.api.controller.result.WebResult;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by liyou on 17/4/25.
 */
public class UserDeviceListResult extends WebResult{

    @ApiModelProperty(value = "结果集", dataType = "com.ii.iintelligence.api.controller.vo.user.UserDeviceVo")
    private List<UserDeviceVo> voList;

    public List<UserDeviceVo> getVoList() {
        return voList;
    }

    public void setVoList(List<UserDeviceVo> voList) {
        this.voList = voList;
    }

    @Override
    public String toString() {
        return "UserDeviceListResult{" +
                "voList=" + voList +
                '}';
    }
}
