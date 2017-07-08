package com.ii.iintelligence.api.controller.vo.switchgear;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
public class SwitchEventVo {

    @ApiModelProperty(value = "设备ID", dataType = "com.ii.iintelligence.api.controller.vo.switchgear.SwitchVo", required = true)
    private SwitchVo switchVo;
    @ApiModelProperty(value = "记录事件交付状态")
    private String deliveryStatus;

    public SwitchVo getSwitchVo() {
        return switchVo;
    }

    public void setSwitchVo(SwitchVo switchVo) {
        this.switchVo = switchVo;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }


}
