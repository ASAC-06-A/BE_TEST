package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.service.SessionService;
import com.asac.study_hub.util.SessionProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    //@SessionAttribute 는 나중에 jwt를 사용하게 된다면 jwt 검증 어노테이션을 사용할 것임
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @GetMapping("/session")
    public BaseResponse<User> checkSession(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request) {
       //인증된 유저
        return BaseResponse.success(SuccessType.VALID_SESSION, sessionService.getValidUser(cookie.getValue(), request));
    }
}
