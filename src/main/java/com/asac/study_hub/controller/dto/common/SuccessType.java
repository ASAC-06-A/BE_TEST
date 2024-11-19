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


    GETPROFILE(HttpStatus.OK, "프로필 조회 성공"),

    GET_STUDY(HttpStatus.OK, null),
    SAVE_STUDY(HttpStatus.OK, "강의 저장 성공"),
    VALID_SESSION(HttpStatus.OK, "세션 검증 성공"),
    GET_STUDY_BY_CATEGORY(HttpStatus.OK, null),
    UPDATE_SUCCESS(HttpStatus.OK, "강의 수정 성공"),
    DELETE_ALL_SUCCESS(HttpStatus.OK, "강의 다수 삭제 성공"),
    FINDALL(HttpStatus.OK, "전체 조회 성공"),
    GET_ALL_STUDY(HttpStatus.OK, "강의 전체 조회 성공"),

    // 통합여부에 따라 삭제 예정
    UPDATE_PROFILE_SUCCESS(HttpStatus.OK, "수정에 성공하였습니다"),
    GET_PROFILE(HttpStatus.OK, "프로필 검색 성공"),
    DELETE_PROFILE(HttpStatus.OK, "삭제가 완료되었습니다"),
    LOGOUT_PROFILE(HttpStatus.OK, "로그아웃 되었습니다");


    HttpStatus status;
    String message;
}
