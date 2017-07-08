package com.ii.domain.base;

/**
 * Created by liyou on 2017/5/13.
 */
public enum EventType {

    Status("状态事件"), OK("确认事件"), Unkonwn("未知事件");

    private String text;

    EventType(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
