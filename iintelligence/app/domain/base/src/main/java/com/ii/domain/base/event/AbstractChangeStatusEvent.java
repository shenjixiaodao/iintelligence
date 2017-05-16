package com.ii.domain.base.event;


import com.ii.domain.base.EventType;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.util.StringUtils;

/**
 * Created by liyou on 2017/5/12.
 */
public abstract class AbstractChangeStatusEvent<T> implements ChangeStatusEvent<T>{

    private Integer delay;
    private String cronExpression;
    private EventType eventType = EventType.Status;
    private PeriodEnabled periodEnabled;
    /**
     * 可发布的延迟时长, 单位秒
     */
    private final static int POST_DELAY = 20;

    public AbstractChangeStatusEvent(Integer delay) {
        if(delay < 0)
            throw new IllegalArgumentException("delay不能为负");
        this.delay = delay;
        this.cronExpression = null;
        periodEnabled = PeriodEnabled.Yes;
    }

    public AbstractChangeStatusEvent(String cronExpression) {
        if(StringUtils.isEmpty(cronExpression))
            throw new IllegalArgumentException("cronExpression不能为空");
        this.cronExpression = cronExpression;
        periodEnabled = PeriodEnabled.Yes;
        delay = null;
    }

    public enum PeriodEnabled {
        Yes("启用"), No("禁用");
        private String text;
        PeriodEnabled(String text){
            this.text = text;
        }
        public String getText(){
            return this.text;
        }
    }

    public PeriodEnabled periodEnabled(){
        return this.periodEnabled;
    }
    public void periodEnabled(PeriodEnabled periodEnabled){
        this.periodEnabled = periodEnabled;
    }

    @Override
    public Integer delay() {
        return delay;
    }
    public void delay(Integer delay){
        this.delay = delay;
    }

    @Override
    public String cronExpression() {
        return cronExpression;
    }
    public void cronExpression(String cronExpression){
        this.cronExpression = cronExpression;
    }

    @Override
    public boolean isPersistable() {
        return delay > POST_DELAY || cronExpression != null;
    }

    @Override
    public EventType getEventType() {
        return this.eventType;
    }

    @Override
    public boolean canPost() {
        if(!StringUtils.isEmpty(this.cronExpression)) {
            CronTrigger trigger = new CronTrigger(this.cronExpression);
            long nextExecutionTime = trigger.nextExecutionTime(new SimpleTriggerContext()).getTime();
            long currentTime = System.currentTimeMillis();
            delay = (int) (nextExecutionTime - currentTime) / 1000;
            if(delay < POST_DELAY){
                return true;
            }
        } else if(delay < POST_DELAY){
            return true;
        }
        return false;
    }
}
