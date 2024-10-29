package com.asac.study_hub.controller.dto.userDto.signupDto;

import com.asac.study_hub.domain.User;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class SignupRequestDto {

    Integer id;

    @NotBlank
    String userName;

    @Email
    String email;

    @Size(min = 8, max = 20)
    @NotBlank
    String password;

    public static User of(SignupRequestDto userDto) {
        return new User(userDto.getId(), userDto.getUserName(), userDto.getEmail(), userDto.getPassword());
    }
}
