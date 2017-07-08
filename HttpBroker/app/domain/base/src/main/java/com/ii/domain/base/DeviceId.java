package com.ii.domain.base;

import java.util.Objects;

/**
 * Created by liyou on 17/4/18.
 */
public class DeviceId implements Entity<DeviceId>{

    private String id;

    public DeviceId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceId deviceId1 = (DeviceId) o;

        return id.equals(deviceId1.id);

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
    public boolean sameIdentityAs(DeviceId other) {
        if(this == other || Objects.equals(id, other.id()))
            return true;
        return false;
    }

    public DeviceId() {
        //for ORM
    }
}
