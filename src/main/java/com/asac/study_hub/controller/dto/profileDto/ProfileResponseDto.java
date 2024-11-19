package com.asac.study_hub.controller.dto.profileDto;

import com.asac.study_hub.domain.Status;
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
    Status status;

    private ProfileResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.status = user.getStatus();
    }

    public static ProfileResponseDto of(User user) {
        return new ProfileResponseDto(user);
    }
}
