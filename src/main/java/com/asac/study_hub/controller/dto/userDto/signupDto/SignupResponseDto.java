package com.asac.study_hub.controller.dto.userDto.signupDto;

import com.asac.study_hub.domain.User;
import com.asac.study_hub.success.UserSuccessResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignupResponseDto {

    Integer userId;
    String message;
    int status;

    private SignupResponseDto(User user) {
        this.userId = user.getId();
    }

    public static class SignupResponseDtoBuilder {
        public SignupResponseDtoBuilder message(String message) {
            if (!UserSuccessResponse.valueOf("SIGNUP").getMessage().equals(message))  {
                throw new RuntimeException("UserSuccessResponse 객체에 없는 message 입니다. message: " + message);
            }
            this.message = message;
            return this;

        }
    }

    public static SignupResponseDto of(User user) {
        return new SignupResponseDto(user);
    }
}
