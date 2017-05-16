package com.ii.domain.base;

import java.util.Objects;

/**
 * Created by liyou on 17/4/18.
 */
public class GroupId implements Entity<GroupId>{

    public static GroupId Ungrouped = new GroupId("0");

    private String id;

    public GroupId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupId groupId1 = (GroupId) o;

        return id.equals(groupId1.id);

    }

    public String id(){
        return this.id;
    }
    public void id(String id){
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean sameIdentityAs(GroupId other) {
        if(this == other || Objects.equals(id, other.id()))
            return true;
        return false;
    }

    public GroupId() {
        //for ORM
    }
}
