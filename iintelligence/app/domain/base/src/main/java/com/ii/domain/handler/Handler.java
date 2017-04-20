package com.ii.domain.handler;

import com.ect.common.error.Result;

/**
 * Created by liyou on 17/4/17.
 */
public interface Handler<T> {

    /**
     * 响应处理结果
     * @param result
     */
    void resultReadyEvent(Result<T> result);

}
