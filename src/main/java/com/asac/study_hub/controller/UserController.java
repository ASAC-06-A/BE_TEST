package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.service.dbService.UserService;
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
    //cors 어노테이션 적용때문에 일단 메서드 껍데기만 작성하거여서 껍데기 구현하기면 됩니다
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signup")
    public BaseResponse signup() {
        return BaseResponse.success(null, null);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/signin")
    public BaseResponse signin() {
        return BaseResponse.success(null, null);
    }
}
