package com.ii.domain.switchgear;

import com.ii.domain.base.State;

import java.util.Objects;

/**
 * Created by liyou on 17/4/20.
 */
public class SwitchState implements State<SwitchState> {

    private final State state;
    private final Long timestamp;

    public SwitchState(State state, Long timestamp) {
        this.state = state;
        this.timestamp = timestamp;
    }

    public State state(){
        return this.state;
    }

    public Long timestamp(){
        return this.timestamp;
    }

    public enum State{

        On("打开"), Off("关闭"), Unknown("未知");

        private String text;

        State(String text){
            this.text = text;
        }

        public static State codeOf(String name){
            for(State state : State.values()){
                if(Objects.equals(state.toString(), name))
                    return state;
            }
            return Unknown;
        }

        public String getText(){
            return this.text;
        }
    }



    @Override
    public boolean sameStateAs(SwitchState other) {
        return other == null ? false : this.state == other.state();
    }

    @Override
    public boolean reconfirmeState(SwitchState other, int seconds) {
        return Math.abs(this.timestamp - other.timestamp())/1000 < seconds;
    }
}
