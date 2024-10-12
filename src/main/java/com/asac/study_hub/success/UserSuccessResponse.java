package com.asac.study_hub.success;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public enum UserSuccessResponse {
    SIGNUP("회원가입 성공");

    String message;

}
