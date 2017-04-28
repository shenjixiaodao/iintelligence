package com.ii.iintelligence.api.controller.assembler.user;

import com.ii.data.user.query.Entity.UserDeviceEntity;
import com.ii.data.user.query.criteria.UserDeviceCriteria;
import com.ii.domain.user.UserDevice;
import com.ii.iintelligence.api.controller.vo.user.UserDeviceCriteriaVo;
import com.ii.iintelligence.api.controller.vo.user.UserDeviceListResult;
import com.ii.iintelligence.api.controller.vo.user.UserDeviceVo;
import org.springframework.beans.BeanUtils;

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

    public static UserDeviceCriteria toCriteria(UserDeviceCriteriaVo vo){
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        BeanUtils.copyProperties(vo, criteria);
        return criteria;
    }

    public static List<UserDeviceVo> entity2VoList(List<UserDeviceEntity> entities){
        List<UserDeviceVo> voList = new ArrayList<>(entities.size());
        for(UserDeviceEntity entity:entities){
            UserDeviceVo vo = new UserDeviceVo();
            BeanUtils.copyProperties(entity, vo);
            voList.add(vo);
        }
        return voList;
    }
}
