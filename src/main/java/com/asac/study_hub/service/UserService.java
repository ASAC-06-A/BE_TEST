package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.userDto.signinDto.SigninRequestDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.controller.dto.userDto.UserResponseDto;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.asac.study_hub.repository.UserRepository;
import com.asac.study_hub.util.SessionProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;

    public UserResponseDto signup(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        String name = signupRequestDto.getUserName();

        if (checkDuplicatedEmail(email)) {
            throw new CustomException(ExceptionType.EXIST_EMAIL, email);
        }
        if (checkDuplicatedName(name)) {
            throw new CustomException(ExceptionType.EXIST_NICKNAME, name);
        }

        signupRequestDto.setId(userRepository.findAll().size() + 1);
        User user = userRepository.save(SignupRequestDto.of(signupRequestDto));

        return UserResponseDto.builder()
                .userId(user.getId())
                .status(HttpStatus.CREATED.value())
                .build();

    }

    public UserResponseDto signin(HttpServletRequest request, HttpServletResponse response, SigninRequestDto userDto) {

        saveSession(request, userDto);
        User user = userRepository.findByEmail(userDto.getEmail());
        return UserResponseDto.builder()
                .userId(user.getId())
                .status(HttpStatus.OK.value())
                .build();
    }

    private void saveSession(HttpServletRequest request, SigninRequestDto userDto) {
        //로그인 한정 사용하는 메서드 따라서 세션이 있으면 그대로 사용하고 없으면 새로 생성하는 getSession 메서드 사용
        User user = userRepository.findByEmail(userDto.getEmail());
        SessionProvider.createSession(request, user);
    }

    private boolean checkDuplicatedEmail(String email) {
        return userRepository.findAll().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    private boolean checkDuplicatedName(String name) {
        return userRepository.findAll().stream().anyMatch(user -> user.getName().equals(name));
    }
}
