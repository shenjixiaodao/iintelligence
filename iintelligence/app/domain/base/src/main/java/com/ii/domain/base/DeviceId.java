package com.ii.domain.base;

import java.util.Objects;

/**
 * Created by liyou on 17/4/18.
 */
public class DeviceId implements Entity<DeviceId>{

    private String deviceId;

    public DeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceId deviceId1 = (DeviceId) o;

        return deviceId.equals(deviceId1.deviceId);

    }

    @Override
    public int hashCode() {
        return deviceId.hashCode();
    }

    @Override
    public boolean sameIdentityAs(DeviceId other) {
        if(this == other || Objects.equals(deviceId, other.deviceId))
            return true;
        return false;
    }
}
