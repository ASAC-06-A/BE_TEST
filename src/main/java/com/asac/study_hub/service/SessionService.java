package com.asac.study_hub.service;

import com.asac.study_hub.domain.User;
import com.asac.study_hub.util.SessionProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    //세션 검증
    public User getValidUser(String sessionId, HttpServletRequest request) {
        User user = SessionProvider.getValidUser(sessionId, request);
        return user;
    }
}
