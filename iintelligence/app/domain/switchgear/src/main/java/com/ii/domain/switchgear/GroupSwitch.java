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

    public GroupId id(){
        return this.id;
    }
    public void id(GroupId id){
        this.id = id;
    }

    public List<Switch> switches(){
        return this.switches;
    }
    public void switches(List<Switch> switches){
        this.switches = switches;
    }

    @Deprecated
    public GroupSwitch() {
        //for ORM
    }
}
