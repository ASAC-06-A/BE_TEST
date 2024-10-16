package com.asac.study_hub.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private ExceptionType type;

    public CustomException(ExceptionType exceptionType, Object message) {
        super(exceptionType.getMessage() + message.toString());
        this.type = exceptionType;
    }

    public CustomException() {
        super();
    }

    public CustomException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.type = exceptionType;
    }
}
