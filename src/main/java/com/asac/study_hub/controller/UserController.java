package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupResponseDto;
import com.asac.study_hub.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.status(CREATED).body((userService.signup(signupRequestDto)));
    }
}
