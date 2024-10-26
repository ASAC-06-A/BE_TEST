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

    SIGNUP(HttpStatus.CREATED, "회원가입 성공"),
    SIGNIN(HttpStatus.OK, "로그인 성공"),
    GETPROFILE(HttpStatus.OK, "프로필 조회 성공");

    HttpStatus status;
    String message;
}
