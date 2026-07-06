package com.rqh.user.common.exception;

import com.rqh.user.common.errorcode.CommonErrorCode;
import com.rqh.user.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常：出动抛出的、预期内的失败
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusiness(BusinessException e) {
        log.warn("业务异常：code：{}, msg：{}",
                e.getErrorCode().getCode(), e.getErrorCode().getMsg());
        return Result.fail(e.getErrorCode());
    }

    /**
     * 参数校验异常：@Valid 校验失败时触发
     */
    @ExceptionHandler(BindException.class)
    public Result<Void> handleBind(BindException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        log.warn("参数校验失败：{}", msg);
        return Result.fail(CommonErrorCode.PARAMETER_ERROR, msg);
    }

    /**
     * 兜底：所有未预料到的异常
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常，请联系管理员", e);
        return Result.fail(CommonErrorCode.SYSTEM_ERROR);
    }
}
