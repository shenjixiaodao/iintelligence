package com.ii.domain.switchgear;

import java.util.Objects;

/**
 * Created by liyou on 17/4/20.
 */
public class SwitchStatus {

    private Status status;
    private Long timestamp;

    public SwitchStatus(SwitchStatus.Status state, Long timestamp) {
        this.status = state;
        this.timestamp = timestamp;
    }

    public SwitchStatus(Status status) {
        this.status = status;
        timestamp = System.currentTimeMillis();
    }

    public Status status(){
        return this.status;
    }
    public void status(SwitchStatus.Status status){
        this.status = status;
    }

    public Long timestamp(){
        return this.timestamp;
    }

    public enum Status {

        On("打开"), Off("关闭"), Unknown("未知");

        private String text;

        Status(String text){
            this.text = text;
        }

        public static Status codeOf(String name){
            for(Status state : Status.values()){
                if(Objects.equals(state.toString(), name))
                    return state;
            }
            return Unknown;
        }

        public String getText(){
            return this.text;
        }
    }

    @Deprecated
    public SwitchStatus() {
        //for ORM
        timestamp = System.currentTimeMillis();
    }

    public boolean sameStatusAs(SwitchStatus other) {
        return other == null ? false : this.status == other.status();
    }

    public boolean reconfirmStatus(SwitchStatus other, int seconds) {
        return Math.abs(this.timestamp - other.timestamp())/1000 < seconds;
    }
}
