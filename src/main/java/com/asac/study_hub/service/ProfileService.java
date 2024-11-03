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
        User user = Optional.ofNullable(userRepository.findByUserId(willUpdateUser.getId()))
            .orElseThrow(() -> new CustomException(
                ExceptionType.NOT_FOUNT_USER_BY_ID, willUpdateUser.getId()));
        User newUser = requestDto.to();

//        if (!checkAuthorization(user, newUser)) {
//            throw new CustomException(ExceptionType.INVALID_ACCESS);
//        }
        userRepository.updateUser(user, newUser);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

//    private boolean checkAuthorization(User user, User newUser) {
//        //수정할 권한이 있는지 확인
//        return user.getId().equals(newUser.getId());
//    }

}
