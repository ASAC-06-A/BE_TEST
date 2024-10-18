package com.asac.study_hub.controller.dto.common;

import com.asac.study_hub.exception.ExceptionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {

    boolean success;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T body;

    public static <T> BaseResponse<T> success(SuccessType type, T body) {
        return new BaseResponse<T>(true, type.getMessage(), body);
    }
    public static <T> BaseResponse<T> failure(ExceptionType type) {
        return new BaseResponse<T>(false, type.getMessage(), null);
    }

    public static <T> BaseResponse<T> failure(ExceptionType type, T body) {
        return new BaseResponse<T>(false, type.getMessage(), body);
    }
}
