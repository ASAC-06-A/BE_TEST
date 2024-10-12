package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupResponseDto;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.UserRepository;
import com.asac.study_hub.success.UserSuccessResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;

    private static final HashMap<Integer, User> users;
    static {
        users = new HashMap<>();
        users.put(1, new User(1, "김정현", "solee3020@gmail.com", "solee6810"));
        users.put(2, new User(2, "김지연", "jykim9335@gmail.com", "asas5656"));
    }

    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        User user = new User(users.size() + 1, signupRequestDto.getUserName(), signupRequestDto.getEmail(), signupRequestDto.getPassword());
        return SignupResponseDto.builder().userId(user.getId()).message(UserSuccessResponse.SIGNUP.getMessage()).status(HttpStatus.CREATED.value()).build();
    }
}
