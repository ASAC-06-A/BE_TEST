package com.asac.study_hub.util;

import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionProvider {
    private static final String SESSION_ID = "user";
    //1. 쿠케 세션 확인, 세션 만료 확인, 만료되었다면 재로그인, 유효하면 User 객체 사용
    //쿠키에 세션있는지 확인 -> @CookieValue 로 컨트롤러에서 확인 (사용자가 로그아웃 후에 탈취된 쿠키 사용 방지)

    //세션 만료 확인
    public static HttpSession validSession(HttpServletRequest request) {
        try {
            return request.getSession(false); //기존 세션이 만료되어서 없어졌다면 nul을 반환
        } catch (NullPointerException e) {
            log.warn(e.getMessage(), e);
            throw new CustomException(ExceptionType.EXPIRED_SESSION);
        }
    }

    //세션 생성
    public static HttpSession createSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(); //기존 세션이 있다면 기존꺼 사용, 없다면 새로 생성(이전에 setAttribute 해서 SESSIONID로 저장한 User객첻 없어짐)
        if (session.getAttribute(SESSION_ID) != null) {
            return session;
        }
        session.setAttribute(SESSION_ID, user);
        return session;
    }

    public static User getValidUser(HttpSession session) {
        return (User) session.getAttribute(SESSION_ID);
    }


}
