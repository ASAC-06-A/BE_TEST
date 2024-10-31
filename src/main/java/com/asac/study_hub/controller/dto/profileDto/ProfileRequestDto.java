package com.asac.study_hub.controller.dto.profileDto;

import com.asac.study_hub.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileRequestDto {

    @NotBlank
    String username;

    @Size(min = 8, max = 20)
    @NotBlank
    String password;

    public User to() {
        return User.builder()
            .name(this.username)
            .password(this.password)
            .build();
    }

}


