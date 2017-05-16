package com.ii.domain.switchgear;

import com.ii.domain.base.GroupId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 17/4/28.
 */
public class GroupsSwitch {

    private final Map<GroupId, GroupSwitch> groups;

    public GroupsSwitch() {
        this.groups = new HashMap<>();

    }

    /**
     * 未分组开关归为同一组
     * @param s
     */
    public void putIntoNewGroup(Switch s){
        if(groups.get(GroupId.Ungrouped) == null){
            List<Switch> switches = new ArrayList<>();
            switches.add(s);
            groups.put(GroupId.Ungrouped, new GroupSwitch(GroupId.Ungrouped,switches));
        }else {
            groups.get(GroupId.Ungrouped).switches().add(s);
        }
    }

    public void putIntoNewGroup(GroupSwitch groupSwitch){
        groups.put(groupSwitch.id(), groupSwitch);
    }

    public boolean putIntoGroup(Integer groupId, Switch s){
        if(groupId == null)
            throw new IllegalArgumentException("GroupId 不能为空");
        if(s == null)
            throw new IllegalArgumentException("s 不能为空");
        if(groups.get(groupId) != null) {
            groups.get(groupId).switches().add(s);
            return true;
        }
        return false;
    }

    public List<GroupSwitch> groups(){
        return new ArrayList<>(groups.values());
    }
}
