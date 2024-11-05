package com.asac.study_hub.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.asac.study_hub.controller.dto.profileDto.ProfileRequestDto;
import com.asac.study_hub.domain.Status;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.UserRepository;
import com.asac.study_hub.repository.UserRepositoryTest;
import java.util.logging.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = Logger.getLogger(UserRepositoryTest.class.getName());

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
        profileService.updateUser(user,
            profileRequestDto); //세션에 있는 user 객체를 profileRequestDto 데이터로 업데이트

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
        assertThat("12345678").isEqualTo(user.getPassword());

    }

    @DisplayName("유저 삭제")
    @Test
    void delete() {

        User user = new User(43, "user", "user@gmail.com", "12345678", Status.ACTIVE);
        userRepository.save(user);

        logger.info("[before] users is : " + userRepository.findAll());
        profileService.deleteUser(user);
        logger.info("[after] users is : " + userRepository.findAll());


    }
}