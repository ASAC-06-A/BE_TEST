package com.asac.study_hub.controller.dto.userDto;

import com.asac.study_hub.domain.Status;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
        this.user = user.getId();
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user);
    }

    public static class UserResponseDtoBuilder { //사용자 상태에 따라서 예외 발생
        public UserResponseDtoBuilder user(User user) {
            Status status = user.getStatus();
            if (status.equals(Status.ACTIVE)){
                this.user = user.getId();
            } else {
                if (status.equals(Status.INACTIVE)) {
                    log.warn(ExceptionType.INVALID_USER.getMessage() + status.name());
                } else if(status.equals(Status.DELETED)){
                    log.warn(ExceptionType.INVALID_USER.getMessage() + status.name());
                }
                throw new CustomException(ExceptionType.FAILD_SIGNIN);
            }
            return this;
        }
    }
}
