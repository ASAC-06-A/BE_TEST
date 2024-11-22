package com.asac.study_hub.controller.dto.userDto;

import com.asac.study_hub.domain.UserStatus;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {

    @JsonProperty("userId")
    Integer user;
    int status;

    private UserResponseDto(User user) {
        this.user = user.getUserId();
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user);
    }

    public static class UserResponseDtoBuilder { //사용자 상태에 따라서 예외 발생
        public UserResponseDtoBuilder user(User user) {
            UserStatus status = user.getUserStatus();
            if (status.equals(UserStatus.ACTIVE)){
                this.user = user.getUserId();
            } else {
                if (status.equals(UserStatus.INACTIVE)) {
                    log.warn(ExceptionType.INVALID_USER.getMessage() + status.name());
                } else if(status.equals(UserStatus.DELETED)){
                    log.warn(ExceptionType.INVALID_USER.getMessage() + status.name());
                }
                throw new CustomException(ExceptionType.FAILD_SIGNIN);
            }
            return this;
        }
    }
}
