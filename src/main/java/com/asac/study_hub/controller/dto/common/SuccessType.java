package com.asac.study_hub.controller.dto.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessType {
    //성공 상태 다 200으로

    SIGNUP(HttpStatus.CREATED, "회원가입 성공"),
    SIGNIN(HttpStatus.OK, "로그인 성공"),
    GET_STUDY(HttpStatus.OK, null),
    SAVE_STUDY(HttpStatus.OK, "강의 저장 성공"),
    VALID_SESSION(HttpStatus.OK, "세션 검증 성공"),
    GET_STUDY_BY_CATEGORY(HttpStatus.OK, null),
    UPDATE_SUCCESS(HttpStatus.OK, null),
    DELETE_ALL_SUCCESS(HttpStatus.OK, "강의 다수 삭제 성공"),
    GET_ALL_ROADMAP(HttpStatus.OK, "강의 조회 성공");

    HttpStatus status;
    String message;
}
