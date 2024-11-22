package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.userDto.signinDto.SigninRequestDto;
import com.asac.study_hub.controller.dto.userDto.UserResponseDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
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
@Slf4j
public class UserService {

    UserRepository userRepository;

    public UserResponseDto signup(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        String name = signupRequestDto.getUserName();

        if (checkEmailExist(email)) {
            throw new CustomException(ExceptionType.EXIST_EMAIL, email);
        }

        signupRequestDto.setId(userRepository.findAll().size() + 1);
        User user = userRepository.save(SignupRequestDto.of(signupRequestDto));

        return UserResponseDto.builder()
                .user(user)
                .status(HttpStatus.CREATED.value())
                .build();
    }
    public UserResponseDto signin(HttpServletRequest request, HttpServletResponse response, SigninRequestDto userDto) {

        saveSession(request, userDto);
        if (!checkEmailExist(userDto.getEmail())) { //메모리에 해당 이메일로 회원가입한 데이터가 없다면
            log.warn(ExceptionType.NOT_FOUNT_USER_BY_EMAIL.getMessage() + userDto.getEmail()); //내부고객(동료 개발자)에게 어떤 정보가 틀렸는지 로그로 알려주기
            throw new CustomException(ExceptionType.FAILD_SIGNIN);
        }

        User user = userRepository.findByEmail(userDto.getEmail());

        if (!validPassword(user, userDto)) {
            log.warn(ExceptionType.WRONG_PASSWORD.getMessage());
            throw new CustomException(ExceptionType.FAILD_SIGNIN);
        }
        return UserResponseDto.builder()
                .user(user)
                .status(HttpStatus.OK.value())
                .build();
    }

    private void saveSession(HttpServletRequest request, SigninRequestDto userDto) {
        //로그인 한정 사용하는 메서드 따라서 세션이 있으면 그대로 사용하고 없으면 새로 생성하는 getSession 메서드 사용
        User user = userRepository.findByEmail(userDto.getEmail());
        SessionProvider.createSession(request, user);
    }

    private boolean checkEmailExist(String email) {
        return userRepository.findAll().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    private boolean validPassword(User user, SigninRequestDto userDto) {
        return user.getPassword().equals(userDto.getPassword());
    }

}
