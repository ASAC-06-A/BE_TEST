package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.controller.dto.userDto.UserResponseDto;
import com.asac.study_hub.controller.dto.userDto.signinDto.SigninRequestDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/user")
@Slf4j
public class UserController {

    UserService userService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/signup")
    public BaseResponse<UserResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return BaseResponse.success(SuccessType.SIGNUP, userService.signup(signupRequestDto));
    }

    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @PostMapping("/signin")
    public BaseResponse<UserResponseDto> signin(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid SigninRequestDto userDto) {
        return BaseResponse.success(SuccessType.SIGNIN, userService.signin(request, response, userDto));
    }
}
