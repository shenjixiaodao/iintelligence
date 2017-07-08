package com.ii.domain.base.event;


import com.ii.domain.base.DomainEvent;

/**
 * Created by liyou on 17/4/19.
 */
public interface ChangeStatusEvent<T> extends DomainEvent<T> {

    T event();

    /**
     * 获取该事件延迟处理时间, 单位为秒;
     * 对于延迟时间过长的事件，为了防止内存溢出，也应当考虑持久化策略
     * @return 延长时间
     */
    Integer delay();

    /**
     * 以cron表达式描述的周期事件, 为了防止内存溢出, 该类事件应当做持久化处理,
     * @return cron expression
     */
    String cronExpression();

    boolean isPersistable();

    /**
     * 该事件是否满足 post 条件
     * @return
     */
    boolean canPost();
}
