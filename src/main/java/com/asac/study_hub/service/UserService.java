package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupResponseDto;
import com.asac.study_hub.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        signupRequestDto.setId(userRepository.findAll().size() + 1);
        return SignupResponseDto.builder()
                .userId(userRepository.save(SignupRequestDto.of(signupRequestDto)).getId())
                .status(HttpStatus.CREATED.value())
                .build();

    }

    public SignupResponseDto signin(HttpServletRequest request, HttpServletResponse response, SignupRequestDto userDto) {

        saveSession(request, userDto);

        Cookie cookie = new Cookie("session_key", sessionId);
        response.addCookie(cookie);

        return SignupResponseDto.builder()
                .userId(SignupRequestDto.of(userDto).getId())
                .status(HttpStatus.OK.value())
                .build();
    }

    private void saveSession(HttpServletRequest request, SignupRequestDto userDto) {
        //로그인 한정 사용하는 메서드 따라서 세션이 있으면 그대로 사용하고 없으면 새로 생성하는 getSession 메서드 사용
        HttpSession session = request.getSession();
        if (session.getAttri)
        session.setAttribute("session_key", userDto);
    }
}
