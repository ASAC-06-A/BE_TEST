package com.asac.study_hub.repository;

import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import java.util.logging.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {

    private static final Logger logger = Logger.getLogger(UserRepositoryTest.class.getName());
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    public void deleteUser_ShouldRemoveUserFromUsersMap() {
        // Given
        User user = userRepository.findByUserId(1); // test용으로 id가 1인 유저 가져오기

        logger.info("[1]Before deletion: " + userRepository.findAll());
        logger.info("[2]Before deletion, user is : " + user);

        // 웬
        userRepository.delete(user);

        logger.info("[1]after deletion: " + userRepository.findAll());

        //덴
        Assertions.assertThatThrownBy(() -> userRepository.findByUserId(1))
            .isInstanceOf(CustomException.class)
            .hasMessageContaining(ExceptionType.NOT_FOUNT_USER_BY_ID.getMessage());

    }
}