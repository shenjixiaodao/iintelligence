package com.ii.domain.continuation;

import com.ect.common.error.Result;

/**
 * Created by liyou on 17/4/17.
 */
public interface ContinuationHandler<T> {

    /**
     * 响应处理结果
     * @param result
     */
    void resultReadyEvent(Result<T> result);

}
