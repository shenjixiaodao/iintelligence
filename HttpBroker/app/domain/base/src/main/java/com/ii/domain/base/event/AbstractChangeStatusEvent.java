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
    //记录事件交付状态
    private DeliveryStatus deliveryStatus;
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

    /**
     * 对于一次性事件， 当有些场景需要保证事件是否已交付完成，
     * 此时需要记录事件的交付状态，确保事件是否已收到设备的{@link EventType#OK}响应。
     * 如果事件已到发布时间且已发布时间，但状态仍为{@link DeliveryStatus#Waiting}, 则需要设置处理策略:
     *  1)重新发布事件; 2)超时T扔未{@link DeliveryStatus#Finished}
     */
    public enum DeliveryStatus {
        Waiting("等待发布"), Finished("交付完成"), Optional("不要求的");
        private String text;
        DeliveryStatus(String text){
            this.text = text;
        }
        public static DeliveryStatus codeOf(String code){
            for(DeliveryStatus status:DeliveryStatus.values()){
                if(code.equals(status.toString()))
                    return status;
            }
            throw new IllegalArgumentException("未知交付状态");
        }
        public String getText(){
            return this.text;
        }
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

    public DeliveryStatus deliveryStatus(){
        return this.deliveryStatus;
    }
    public void deliveryStatus(DeliveryStatus deliveryStatus){
        this.deliveryStatus = deliveryStatus;
    }
    public void deliveryStatus(String deliveryStatus){
        this.deliveryStatus = DeliveryStatus.codeOf(deliveryStatus);
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
