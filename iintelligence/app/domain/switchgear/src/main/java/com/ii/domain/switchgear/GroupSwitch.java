package com.ii.domain.switchgear;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyou on 17/4/28.
 */
public class GroupSwitch {

    private final Map<Integer, Group> groups;
    private int currentGroupId = 0;

    public GroupSwitch() {
        this.groups = new HashMap<>();

    }

    public void putIntoNewGroup(Switch s){
        currentGroupId++;
        List<Switch> switches = new ArrayList<>();
        switches.add(s);
        groups.put(currentGroupId, new Group(currentGroupId,switches));
    }

    public void putIntoNewGroup(List<Switch> switches){
        currentGroupId++;
        groups.put(currentGroupId, new Group(currentGroupId,switches));
    }

    public boolean putIntoGroup(Integer groupId, Switch s){
        if(groupId == null)
            throw new IllegalArgumentException("groupId 不能为空");
        if(s == null)
            throw new IllegalArgumentException("s 不能为空");
        if(groups.get(groupId) != null) {
            groups.get(groupId).switches().add(s);
            return true;
        }
        return false;
    }

    public List<Group> groups(){
        return new ArrayList<>(groups.values());
    }

    public class Group {

        private final Integer id;
        private final List<Switch> switches;

        public Group(Integer id, List<Switch> switches) {
            if (id == null)
                throw new IllegalArgumentException("groupID不能为空");
            this.id = id;
            this.switches = switches;
        }

        public Integer id(){
            return this.id;
        }

        public List<Switch> switches(){
            return this.switches;
        }
    }
}
