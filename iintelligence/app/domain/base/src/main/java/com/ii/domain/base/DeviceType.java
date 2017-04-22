package com.ii.domain.base;

import java.util.Objects;

/**
 * Created by liyou on 17/4/22.
 */
public enum DeviceType {
    Switch("开关"), Unknown("未知");

    private String text;

    DeviceType(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
    public static DeviceType codeOf(String name){
        for(DeviceType type : DeviceType.values()){
            if(Objects.equals(type.toString(), name))
                return type;
        }
        return Unknown;
    }
}
