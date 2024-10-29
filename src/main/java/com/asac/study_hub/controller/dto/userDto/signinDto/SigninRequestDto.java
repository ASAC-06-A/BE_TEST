package com.asac.study_hub.controller.dto.userDto.signinDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class SigninRequestDto {

    @Email
    String email;

    @Size(min = 8, max = 20)
    @NotBlank
    String password;

}
