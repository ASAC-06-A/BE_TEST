package com.asac.study_hub.util;

import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class SessionProvider {
    private static final String SESSION_ID = "user";
    //1. 쿠케 세션 확인, 세션 만료 확인, 만료되었다면 재로그인, 유효하면 User 객체 사용
    //쿠키에 세션있는지 확인 -> @CookieValue 로 컨트롤러에서 확인

    //세션 만료 확인 -> 로그인 후 모든 요청에 사용
    public static User validSession(HttpSession session) {
        User user = (User) session.getAttribute(SESSION_ID);
        return Optional.ofNullable(user).orElseThrow(() -> new CustomException(ExceptionType.EXPIRED_SESSION));
    }

    //세션 생성 -> 로그인시 사용
    public static HttpSession createSession(HttpServletRequest request) {
        return request.getSession();
    }


}
