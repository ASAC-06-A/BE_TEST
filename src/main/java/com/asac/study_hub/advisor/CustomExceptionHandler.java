package com.asac.study_hub.advisor;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public BaseResponse<Void> handler(CustomException e) {
        log.atLevel(e.getType().getLevel()).setCause(e).log(e.getMessage());
        return BaseResponse.failure(e.getType());
    }

    @ExceptionHandler
    public BaseResponse<Void> handler(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResponse.failure(ExceptionType.UNCLASSIFIED_ERROR);
    }

}
