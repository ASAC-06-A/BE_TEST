package com.asac.study_hub.advisor;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.FieldErrorDto;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public BaseResponse<Void> handler(CustomException e) {
        log.atLevel(e.getType().getLevel()).setCause(e).log(e.getMessage(), e);
        return BaseResponse.failure(e.getType());
    }

    @ExceptionHandler
    public BaseResponse<List<FieldErrorDto>> handler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldErrorDto> errorList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        bindingResult.getFieldErrors()
                .forEach((error) -> {
                    errorList.add(new FieldErrorDto(error.getField(), error.getDefaultMessage()));
                    sb.append("[")
                            .append(error.getField()) //예외가 발생한 필드
                            .append("]")
                            .append(error.getDefaultMessage());
                });
        log.warn(sb.toString(), e);
        return BaseResponse.failure(ExceptionType.INVALID_INPUT, errorList);
    }

    @ExceptionHandler
    public BaseResponse<Void> handler(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResponse.failure(ExceptionType.UNCLASSIFIED_ERROR);
    }

    @ExceptionHandler
    public BaseResponse<Void> handler(MissingRequestCookieException e) {
        log.warn(e.getMessage(), e);
        return BaseResponse.failure(ExceptionType.NOT_EXIST_SESSION_ID);
    }

}
