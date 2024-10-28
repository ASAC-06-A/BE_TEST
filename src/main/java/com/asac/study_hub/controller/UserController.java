package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupResponseDto;
import com.asac.study_hub.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/user")
@Slf4j
public class UserController {

    UserService userService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/signup")
    public BaseResponse<SignupResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return BaseResponse.success(SuccessType.SIGNUP, userService.signup(signupRequestDto));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/signin")
    public BaseResponse<SignupResponseDto> signin(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid SignupRequestDto userDto) {
        return BaseResponse.success(SuccessType.SIGNIN, userService.signin(request, response, userDto));
    }
}
