package com.asac.study_hub.util;

import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionProvider {

    private static final String SESSION_ID = "user";
    //1. 쿠케 세션 확인, 세션 만료 확인, 만료되었다면 재로그인, 유효하면 User 객체 사용
    //쿠키에 세션있는지 확인 -> @CookieValue 로 컨트롤러에서 확인 (사용자가 로그아웃 후에 탈취된 쿠키 사용 방지)

    //세션 만료 확인
    public static HttpSession getValidSession(HttpServletRequest request) {
        return Optional.ofNullable(request.getSession(false))
            .orElseThrow(() -> new CustomException(ExceptionType.EXPIRED_SESSION));
    }

    //세션 생성
    //로그인할때만 사용됨
    public static HttpSession createSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(); //기존 세션이 있다면 기존꺼 사용, 없다면 새로 생성(이전에 setAttribute 해서 SESSIONID로 저장한 User객첻 없어짐)
        if (session.getAttribute(session.getId()) == null) {
            session.setAttribute(session.getId(), user);
        }
        return session;
    }

    public static User getValidUser(String sessionId, HttpServletRequest request) {
        //기존 세션이 만료되어서 없어졌다면 null 을 반환
        HttpSession session = getValidSession(request);
        return (User) Optional.ofNullable(session.getAttribute(sessionId))
            .orElseThrow(() -> new CustomException(ExceptionType.INVALID_SESSION));
    }

    public static void removeSession(/*String sessionId,*/ HttpServletRequest request) {
        request.getSession().invalidate(); // 세션 완전 무효화, 멀티 로그인 구현 시, 전무 로그아웃시 사용하면 좋을듯

    }
}

// request.getSession(false) :기존 세션이 있을 때 기존 세션 반환, 없으면 null 반환
// request.getSession(true) : 기존 세션이 있을 때 기존 세션 반환, 없으면 새로운 세션 생성