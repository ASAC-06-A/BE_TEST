package com.asac.study_hub.service;

import com.asac.study_hub.domain.User;
import com.asac.study_hub.util.SessionProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public User getUser(HttpServletRequest request) {
        HttpSession session = SessionProvider.validSession(request);
        User user = SessionProvider.getValidUser(session);
        SessionProvider.createSession(request, user);

        return user;
    }
}
