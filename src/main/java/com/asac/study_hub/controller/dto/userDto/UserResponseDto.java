package com.asac.study_hub.controller.dto.userDto;

import com.asac.study_hub.domain.User;
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
public class UserResponseDto {

    Integer userId;
    int status;

    private UserResponseDto(User user) {
        this.userId = user.getId();
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user);
    }
}
