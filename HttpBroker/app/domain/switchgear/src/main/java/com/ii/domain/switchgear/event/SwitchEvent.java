package com.ii.domain.switchgear.event;


import com.ii.domain.base.event.AbstractChangeStatusEvent;
import com.ii.domain.switchgear.Switch;
import org.springframework.util.Assert;

/**
 * Created by liyou on 17/4/18.
 */
public class SwitchEvent extends AbstractChangeStatusEvent<SwitchEvent> {

    private Switch s;

    public Switch s(){
        return s;
    }
    public void s(Switch s){
        this.s = s;
    }

    public SwitchEvent(Switch s) {
        this(s,0);
    }

    public SwitchEvent(Switch s, Integer delay) {
        super(delay);
        Assert.notNull(s, "s为空");
        this.s = s;
    }

    public SwitchEvent(Switch s, String cronExpression) {
        super(cronExpression);
        Assert.notNull(s, "s为空");
        this.s = s;
    }

    @Deprecated
    public SwitchEvent() {
        //for ORM
        super(0);
    }

    @Override
    public boolean sameEventAs(SwitchEvent other) {
        return false;
    }

    @Override
    public SwitchEvent event() {
        return this;
    }

}
