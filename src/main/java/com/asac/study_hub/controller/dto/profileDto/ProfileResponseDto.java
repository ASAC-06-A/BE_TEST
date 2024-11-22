package com.asac.study_hub.controller.dto.profileDto;

import com.asac.study_hub.domain.UserStatus;
import com.asac.study_hub.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileResponseDto {

    Integer userId;
    String name;
    String email;
    String password;
    UserStatus status;

    private ProfileResponseDto(User user) {
        this.userId = user.getUserId();
//        this.name = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.status = user.getUserStatus();
    }

    public static ProfileResponseDto of(User user) {
        return new ProfileResponseDto(user);
    }
}
