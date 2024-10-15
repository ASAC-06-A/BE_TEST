package com.asac.study_hub.controller.dto.userDto.signupDto;

import com.asac.study_hub.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class SignupRequestDto {

    Integer id;
    @JsonProperty(value = "user_name")
    String userName;
    String email;
    String password;

    public static User of(SignupRequestDto userDto) {
        return new User(userDto.getId(), userDto.getUserName(), userDto.getEmail(), userDto.getPassword());
    }
}
