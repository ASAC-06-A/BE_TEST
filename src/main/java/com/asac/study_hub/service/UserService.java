package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupResponseDto;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


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
        User user = userRepository.save(SignupRequestDto.of(signupRequestDto));

        return SignupResponseDto.builder()
                .userId(user.getId())
                .status(HttpStatus.CREATED.value())
                .build();

    }

    public SignupResponseDto signin(HttpServletRequest request, HttpServletResponse response, SignupRequestDto userDto) {

        saveSession(request, userDto);

        return SignupResponseDto.builder()
                .userId(SignupRequestDto.of(userDto).getId())
                .status(HttpStatus.OK.value())
                .build();
    }

    private void saveSession(HttpServletRequest request, SignupRequestDto userDto) {
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
