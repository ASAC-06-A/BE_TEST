package com.asac.study_hub.controller.dto.userDto.signupDto;

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

    @JsonProperty(value = "user_name")
    String userName;
    String email;
    String password;
}
