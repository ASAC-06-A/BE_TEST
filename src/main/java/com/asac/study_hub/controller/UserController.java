package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/users")
@Slf4j
public class UserController {

    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.status(CREATED).body(userService.signup(signupRequestDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse> signin(HttpServletRequest request, HttpServletResponse response, @RequestBody SignupRequestDto userDto) {
        return ResponseEntity.status(OK).body(userService.signin(request, response, userDto));
    }
}
