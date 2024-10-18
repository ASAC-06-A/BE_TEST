package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupResponseDto;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
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
        String email = signupRequestDto.getEmail();
        String name = signupRequestDto.getUserName();

        if (checkDuplicatedEmail(email)) {
            throw new CustomException(ExceptionType.EXIST_EMAIL, email);
        }
        if (checkDuplicatedName(name)) {
            throw new CustomException(ExceptionType.EXIST_NICKNAME, name);
        }

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

    private boolean checkDuplicatedEmail(String email) {
        return userRepository.findAll().stream().noneMatch(user -> user.getEmail().equals(email));
    }

    private boolean checkDuplicatedName(String name) {
        return userRepository.findAll().stream().noneMatch(user -> user.getName().equals(name));
    }
}
