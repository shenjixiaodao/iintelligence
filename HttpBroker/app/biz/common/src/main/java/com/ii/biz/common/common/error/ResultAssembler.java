/**
 * 
 */
package com.ii.biz.common.common.error;

import com.ect.common.error.CommonError;
import com.ect.common.error.ErrorCode;
import com.ect.common.error.ErrorContext;
import com.ect.common.error.Result;
import com.ect.common.error.util.ErrorUtil;


public class ResultAssembler {

    public static void makeErrorResult(Result result, String errorKey) {
        result.setSuccess(false);
        ErrorCode errorCode = new ErrorCode(ErrorResoures.getErrorCode(errorKey));
        CommonError commonError = new CommonError(errorCode,"msg","location");
        ErrorContext error = ErrorUtil.addError(commonError);
        result.setErrorContext(error);
    }

    /*public static <T> void makeErrorResult(Result<T> result, Throwable e,MessageSource messageSource) {
        result.setSuccess(false);
        CommonError commonError = ExceptionManager.getException(e,messageSource);
        ErrorContext error = ErrorUtil.addError(commonError);
        result.setErrorContext(error);
    }

    public static <T> void makeErrorResult(Result<T> result, ErrorCode errorCode, String[] params,MessageSource messageSource) {
        result.setSuccess(false);
        try {
            ExceptionManager.publish(errorCode, params);
        } catch (BusinessException e) {
            CommonError commonError = ExceptionManager.getException(e,messageSource);
            ErrorContext error = ErrorUtil.addError(commonError);
            result.setErrorContext(error);
        }

    }


    *//**
     * 自定义错误信息提示
     * @param exception
     * @param local
     * @return
     *//*
    public static ErrorContext makeErrorCode(String exception, String local) {
        ErrorContext errorContext = new ErrorContext();
        ErrorCode errorCode = ErrorUtil.makeErrorCode(ErrorLevel.ERROR, ErrorType.SYSTEM, "000", "001");
        CommonError commonError = null;
        commonError = new CommonError(errorCode, exception, local);
        errorContext.addError(commonError);
        return errorContext;
    }*/
}
