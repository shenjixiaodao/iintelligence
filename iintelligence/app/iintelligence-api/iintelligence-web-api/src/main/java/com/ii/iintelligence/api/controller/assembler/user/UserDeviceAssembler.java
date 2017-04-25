package com.ii.iintelligence.api.controller.assembler.user;

import com.ii.domain.user.UserDevice;
import com.ii.iintelligence.api.controller.vo.user.UserDeviceListResult;
import com.ii.iintelligence.api.controller.vo.user.UserDeviceVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyou on 17/4/25.
 */
public class UserDeviceAssembler {
    private UserDeviceAssembler() {
    }

    public static UserDeviceVo toVo(UserDevice userDevice){
        UserDeviceVo vo = new UserDeviceVo();
        vo.setUid(userDevice.userId().uid());
        vo.setDeviceId(userDevice.device().deviceId().id());
        vo.setDeviceType(userDevice.device().type().toString());
        vo.setDeviceStatus(userDevice.status().toString());
        return vo;
    }

    public static List<UserDeviceVo> toVoList(List<UserDevice> userDevices){
        List<UserDeviceVo> voList = new ArrayList<>(userDevices.size());
        for(UserDevice userDevice : userDevices){
            UserDeviceVo vo = toVo(userDevice);
            voList.add(vo);
        }
        return voList;
    }
}
