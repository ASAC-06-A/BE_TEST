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

        String sessionId = createSession(request, userDto);
        userRepository.saveSession(sessionId, SignupRequestDto.of(userDto));

        Cookie cookie = new Cookie("session_key", sessionId);
        response.addCookie(cookie);

        return SignupResponseDto.builder()
                .userId(SignupRequestDto.of(userDto).getId())
                .status(HttpStatus.OK.value())
                .build();
    }

    private String createSession(HttpServletRequest request, SignupRequestDto userDto) {
        HttpSession session = request.getSession();
        String sessionId = UUID.randomUUID().toString();
        session.setAttribute("session_key", userDto);
        return sessionId;
    }
}
