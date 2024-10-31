package com.asac.study_hub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import com.asac.study_hub.controller.dto.profileDto.ProfileRequestDto;
import com.asac.study_hub.domain.Status;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProfileServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProfileService profileService;

    private User existingUser;
    private ProfileRequestDto profileRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        existingUser = User.builder()
            .id(1)
            .name("김정현")
            .email("solee3020@gmail.com")
            .password("solee6810")
            .status(Status.ACTIVE)
            .build();

        profileRequestDto = ProfileRequestDto.builder()
            .username("김정현_수정")
            .password("newpassword123")
            .build();
    }

    @Test
    void testUpdateUser() {
        // Given
        when(userRepository.findByUserId(existingUser.getId())).thenReturn(existingUser);
        doAnswer(invocation -> {
            User user = invocation.getArgument(0);
            User updatedUser = invocation.getArgument(1);
            user.update(updatedUser);
            return null;
        }).when(userRepository).updateUser(any(User.class), any(User.class));

        // When
        profileService.updateUser(existingUser, profileRequestDto);

        // Then
        assertEquals("김정현_수정", existingUser.getName());
        assertEquals("updated_email@gmail.com", existingUser.getEmail());
        assertEquals("newpassword123", existingUser.getPassword());

        // 업데이트된 사용자 출력
        System.out.println("Updated User: " + existingUser);
    }
}