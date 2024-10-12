package com.asac.study_hub.advisor;

import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionType> handler(CustomException e) {
        log.atLevel(e.getType().getLevel()).setCause(e).log(e.getMessage());
        return ResponseEntity.status(e.getType().getStatus()).body(e.getType());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionType> handler(Exception e) {
        log.error(e.getMessage(), e);
        ExceptionType type = ExceptionType.UNCLASSIFIED_ERROR;
        return ResponseEntity.status(type.UNCLASSIFIED_ERROR.getStatus()).body(type);
    }

}
