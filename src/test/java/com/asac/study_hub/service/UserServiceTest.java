package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.asac.study_hub.service.memoryService.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @DisplayName("회원가입 이메일 중복 확인")
    @Test
    void signup() {
        //given
        SignupRequestDto user1 = new SignupRequestDto();
        user1.setUserName("김지연1");
        user1.setEmail("jykimv@gmail.com");
        user1.setPassword("123456789");

        SignupRequestDto user2 = new SignupRequestDto();
        user2.setUserName("김지연2");
        user2.setEmail("jykimv@gmail.com");
        user2.setPassword("123456789");

        //when
        userService.signup(user1);
        //then
        Assertions.assertThatThrownBy(() -> userService.signup(user2))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("type", ExceptionType.EXIST_EMAIL);

    }
}