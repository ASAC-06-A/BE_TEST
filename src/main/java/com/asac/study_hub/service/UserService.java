package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupResponseDto;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.UserRepository;
import com.asac.study_hub.success.UserSuccessResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;

    private static final HashMap<String, User> sessionStorage;
    private static final HashMap<Integer, User> users;
    static {
        users = new HashMap<>();
        users.put(1, new User(1, "김정현", "solee3020@gmail.com", "solee6810"));
        users.put(2, new User(2, "김지연", "jykim9335@gmail.com", "asas5656"));

        sessionStorage = new HashMap<>();
    }

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        User user = new User(users.size() + 1, signupRequestDto.getUserName(), signupRequestDto.getEmail(), signupRequestDto.getPassword());
        return SignupResponseDto.builder().userId(user.getId()).message(UserSuccessResponse.SIGNUP.getMessage()).status(HttpStatus.CREATED.value()).build();
    }

    public User signin(HttpServletRequest request, HttpServletResponse response, User user) {

        HttpSession session = request.getSession();
        String sessionId = UUID.randomUUID().toString();

        session.setAttribute("session_key", user);
        //session을 인메모리에 저장할 필요가 있나? 서블릿에서 제공해주는 httpSession을 활용해서 어노테이션을 사용하면 만료시간까지만 유효한 세션이 생성됨.
        sessionStorage.put(sessionId, user);

        Cookie cookie = new Cookie("session_key", sessionId);
        response.addCookie(cookie);

        return user;
    }

}
