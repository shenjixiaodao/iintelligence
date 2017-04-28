package com.ii.biz.switchgear.service;

import com.ii.domain.switchgear.GroupSwitch;

/**
 * Created by liyou on 17/4/28.
 */
public interface IUserSwitchService {

    /**
     * 获取按照规则分组的开关
     * @param uid
     * @return
     */
    GroupSwitch findGroupSwitch(String uid);

}
