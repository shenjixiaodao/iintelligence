package com.ii.biz.switchgear.service.impl;

import com.ii.biz.switchgear.common.SwitchHandlerHolder;
import com.ii.biz.switchgear.service.IUserSwitchService;
import com.ii.biz.user.service.IUserDeviceService;
import com.ii.data.user.criteria.UserDeviceCriteria;
import com.ii.domain.base.DeviceType;
import com.ii.domain.handler.Handler;
import com.ii.domain.handler.SwitchHandler;
import com.ii.domain.handler.SwitchesHandler;
import com.ii.domain.switchgear.GroupSwitch;
import com.ii.domain.user.UserDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 17/4/28.
 */
@Service
public class UserSwitchServiceImpl implements IUserSwitchService {

    @Autowired
    private IUserDeviceService userDeviceService;

    /**
     * 返回由{@link SwitchHandler#getSwitch()}和{@link SwitchesHandler#getSwitches()}的返回结果进行分组的分组开关。
     * @param uid
     * @return
     */
    @Override
    public GroupSwitch findGroupSwitch(String uid) {
        if(StringUtils.isEmpty(uid))
            throw new IllegalArgumentException("uid不能为空");
        /**
         * 获取用户所有{@link UserDevice.DeviceStatus.Bind}的开关设备
         */
        UserDeviceCriteria criteria = new UserDeviceCriteria();
        criteria.setDeviceType(uid);
        criteria.setDeviceType(DeviceType.Switch.toString());
        criteria.setDeviceStatus(UserDevice.DeviceStatus.Binding.toString());
        List<UserDevice> userDevices = userDeviceService.findUserDevice(criteria);
        /**
         * 获取所有的handler实例, 对handler持有的switch进行分组
         */
        GroupSwitch groupSwitch = new GroupSwitch();
        Map<Handler, Boolean> handlers = new HashMap<>();//过滤重复
        for(UserDevice userDevice : userDevices){
            Handler handler = SwitchHandlerHolder.getHolder().getHandler(userDevice.device().deviceId());
            if (handler != null)
                handlers.put(handler, Boolean.TRUE);
        }
        for(Map.Entry<Handler,Boolean> entry: handlers.entrySet()){
            Handler handler = entry.getKey();
            if(handler instanceof SwitchHandler){
                groupSwitch.putIntoNewGroup(((SwitchHandler) handler).getSwitch());
            } else if(handler instanceof SwitchesHandler) {
                groupSwitch.putIntoNewGroup(((SwitchesHandler) handler).getSwitches());
            }
        }
        return groupSwitch;
    }

}
