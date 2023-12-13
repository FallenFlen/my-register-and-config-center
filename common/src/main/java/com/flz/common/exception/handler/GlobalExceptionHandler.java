package com.flz.common.exception.handler;

import com.flz.common.dto.ErrorResult;
import com.flz.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleBusinessException(BusinessException e) {
        log.error("BusinessException:", e);
        return ErrorResult.of(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResult handleThrowable(Throwable throwable) {
        log.error("Unknown error:", throwable);
        return ErrorResult.of(throwable.getMessage());
    }
}
