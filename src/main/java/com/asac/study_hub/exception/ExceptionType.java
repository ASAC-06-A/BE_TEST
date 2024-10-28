package com.asac.study_hub.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public enum ExceptionType {
    EXIST_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다. email: ", "exist email account", Level.WARN),
    EMPTY_ID_FIELD(HttpStatus.BAD_REQUEST, "아이디는 필수 입력 값입니다.", "not allowed empty user_id", Level.WARN),
    EXIST_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임입니다. name: ", "exist nickname", Level.WARN),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "올바른 이메일 형식이 아닙니다. email: ", "Invalid email format", Level.WARN),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호는 8자 이상 20자 이하로 작성해주세요.", "Invalid password", Level.WARN),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력 값입니다","Invalid Input", Level.WARN),
    NOT_FOUND_STUDY_BY_ID(HttpStatus.NOT_FOUND, "해당 id로 등록된 강의가 존재하지 않습니다. id: ", "not found study", Level.WARN),
    NOT_EXIST_SESSION_ID(HttpStatus.BAD_REQUEST, "Cookie 에 세션 Id가 없습니다.", "not exist session id", Level.WARN),
    EXPIRED_SESSION(HttpStatus.BAD_REQUEST, "만료된 세션 id 입니다.", "expired session", Level.WARN),
    INVALID_SESSION(HttpStatus.BAD_REQUEST, "잘못된 형식의 session id 입니다.", "invalid session id", Level.WARN),
    NOT_FOUNT_USER_BY_EMAIL(HttpStatus.NOT_FOUND, "해당 email로 가입된 유저가 없습니다. email: ", "not found user by email", Level.WARN),
    NOT_FOUNT_USER_BY_ID(HttpStatus.NOT_FOUND, "해당 id의 유저가 없습니다. id: ", "not found user by id", Level.WARN),
    INVALID_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "권한이 없는 접근입니다.", "Invalid Authorization", Level.WARN),
    UNCLASSIFIED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부에서 에러가 발생했습니다.", "Internal server error", Level.ERROR),

    INVALID_ACCESS(HttpStatus.NOT_FOUND, "잘못된 접근 입니다.", "Invalid access", Level.WARN);



    HttpStatus status;
    String message;
    String error;
    Level level;

}
