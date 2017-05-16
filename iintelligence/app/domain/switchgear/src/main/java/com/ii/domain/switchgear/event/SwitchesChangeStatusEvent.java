package com.ii.domain.switchgear.event;


import com.ii.domain.base.event.AbstractChangeStatusEvent;
import com.ii.domain.switchgear.GroupSwitch;
import org.springframework.util.Assert;

/**
 * Created by liyou on 17/4/18.
 */
public class SwitchesChangeStatusEvent extends AbstractChangeStatusEvent<SwitchesChangeStatusEvent> {

    private GroupSwitch groupSwitch;

    public GroupSwitch groupSwitch(){
        return groupSwitch;
    }

    public SwitchesChangeStatusEvent(GroupSwitch groupSwitch) {
        this(groupSwitch, 0);
    }

    /**
     *
     * @param groupSwitch
     * @param delay 延迟时间, 单位秒
     */
    public SwitchesChangeStatusEvent(GroupSwitch groupSwitch, Integer delay) {
        super(delay);
        Assert.notNull(groupSwitch, "groupSwitch为空");
        this.groupSwitch = groupSwitch;
    }

    /**
     *
     * @param groupSwitch
     * @param cronExpression cron周期表达式
     */
    public SwitchesChangeStatusEvent(GroupSwitch groupSwitch, String cronExpression) {
        super(cronExpression);
        Assert.notNull(groupSwitch, "groupSwitch为空");
        this.groupSwitch = groupSwitch;
    }

    @Deprecated
    public SwitchesChangeStatusEvent() {
        //for ORM
        super(0);
    }

    public SwitchesChangeStatusEvent copyWithoutSwitches(){
        GroupSwitch groupSwitch = new GroupSwitch(this.groupSwitch.id(), null);
        return new SwitchesChangeStatusEvent(groupSwitch, this.cronExpression());
    }

    @Override
    public boolean sameEventAs(SwitchesChangeStatusEvent other) {
        return false;
    }

    @Override
    public SwitchesChangeStatusEvent event() {
        return this;
    }

}
