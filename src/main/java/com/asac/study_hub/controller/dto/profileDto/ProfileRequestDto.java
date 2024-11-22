package com.asac.study_hub.controller.dto.profileDto;

import com.asac.study_hub.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor //AllArgsConstructor 만 있어도 역직렬화가 되어야하는데 안되는 이유를 모르겠음 -> 찾아보기
public class ProfileRequestDto {

    @NotBlank
    String username;

    @Size(min = 8, max = 20)
    @NotBlank
    String password;

    public User to() {
        return User.builder()
//            .username(this.username)
            .password(this.password)
            .build();
    }

}


