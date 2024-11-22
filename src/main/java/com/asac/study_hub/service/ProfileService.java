package com.asac.study_hub.service;
import com.asac.study_hub.controller.dto.profileDto.ProfileRequestDto;
import com.asac.study_hub.controller.dto.profileDto.ProfileUpdateRequestDto;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.asac.study_hub.repository.UserRepository;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProfileService {

    UserRepository userRepository;

    public void updateUser(User willUpdateUser, /*Integer id*/ ProfileUpdateRequestDto requestDto) {
        User user = Optional.ofNullable(userRepository.findByUserId(willUpdateUser.getUserId()))
            .orElseThrow(() -> new CustomException(
                ExceptionType.NOT_FOUNT_USER_BY_ID, willUpdateUser.getUserId()));
        User newUser = requestDto.to();
        userRepository.updateUser(user, newUser);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void logoutUser(User user) {
        userRepository.logoutUser(user);
    }

}
