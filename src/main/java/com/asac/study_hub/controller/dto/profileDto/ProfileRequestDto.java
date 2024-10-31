package com.asac.study_hub.controller.dto.profileDto;

import com.asac.study_hub.domain.Status;
import com.asac.study_hub.domain.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProfileRequestDto {

    Integer id;

    @NotBlank
    String userName;

//    @Email
//    String email;

    @Size(min = 8, max = 20)
    @NotBlank
    String password;

    @NotNull
    @Setter
    Enum<Status> status;

    public User to() {
        return User.builder()
            .id(this.id)
            .name(this.userName)
            //.email(this.email) 이메일이 아이디이기에 변경은 안하는게 나을 듯
            .password(this.password)
            .build();
    }

}
