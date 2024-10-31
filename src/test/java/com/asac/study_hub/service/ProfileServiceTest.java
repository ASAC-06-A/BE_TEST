package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.profileDto.ProfileRequestDto;
import com.asac.study_hub.domain.Status;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("유저 일부 업데이트")
    @Test
    void updateAll() {
        //given

        //업데이트할 때 email 변경 가능? -> 협의 후 진행
        ProfileRequestDto profileRequestDto = ProfileRequestDto.builder()
                .username("username")
                .password("123456789")
                .build();
        User user = new User(40, "user", "user@gmail.com", "12345678", Status.ACTIVE);
        userRepository.save(user);

        //when
        profileService.updateUser(user, profileRequestDto); //세션에 있는 user 객체를 profileRequestDto 데이터로 업데이트

        //then
        assertThat(profileRequestDto.getUsername()).isEqualTo(user.getName());
        assertThat(profileRequestDto.getPassword()).isEqualTo(user.getPassword());

        userRepository.delete(user);

    }

    @DisplayName("유저 부분 업데이트")
    @Test
    void update() {
        //given
        ProfileRequestDto profileRequestDto = ProfileRequestDto.builder()
                .username("username")
                .build();
        User user = new User(40, "user", "user@gmail.com", "12345678", Status.ACTIVE);
        userRepository.save(user);

        //when

        profileService.updateUser(user, profileRequestDto);
        //then
        assertThat(profileRequestDto.getUsername()).isEqualTo(user.getName());
        assertThat(profileRequestDto.getPassword()).isEqualTo(user.getPassword());
    }
}