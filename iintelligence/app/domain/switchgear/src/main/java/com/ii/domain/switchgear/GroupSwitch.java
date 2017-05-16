package com.ii.domain.switchgear;

import com.ii.domain.base.GroupId;

import java.util.List;

/**
 * Created by liyou on 2017/5/13.
 */
public class GroupSwitch {

    private GroupId id;
    private List<Switch> switches;

    public GroupSwitch(GroupId id, List<Switch> switches) {
        if (id == null)
            throw new IllegalArgumentException("groupID不能为空");
        this.id = id;
        this.switches = switches;
    }

    public GroupId groupId(){
        return this.id;
    }

    public List<Switch> switches(){
        return this.switches;
    }

    @Deprecated
    public GroupSwitch() {
        //for ORM
    }
}
