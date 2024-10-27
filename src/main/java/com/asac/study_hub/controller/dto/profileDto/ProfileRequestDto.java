package com.asac.study_hub.controller.dto.profileDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProfileRequestDto {
    Integer id;

    @NotBlank
    @JsonProperty(value = "user_name")
    String userName;

    @Email
    String email;

    @Size(min = 8, max = 20)
    @NotBlank
    String password;

}
