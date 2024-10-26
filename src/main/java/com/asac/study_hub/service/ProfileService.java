package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.profileDto.ProfileRequestDto;
import com.asac.study_hub.controller.dto.profileDto.ProfileResponseDto;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProfileService {

    UserRepository userRepository;

    public ProfileResponseDto getMyProfile(HttpServletRequest request){

        // test용 코드, 세션에 임의로 추가
        User testSessionUser = new User(1,"김정현", "solee3020@gmail.com", "solee6810");
        userRepository.saveSession("1", testSessionUser);


        String sessionId = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
            .filter(cookie -> "session_key".equals(cookie.getName()))
            .map(Cookie::getValue)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효한 세션이 존재하지 않습니다."));

        User myProfile = userRepository.searchMyProfile(sessionId);
        if (myProfile == null) {
            throw new IllegalArgumentException("해당 세션에 대한 사용자 프로필을 찾을 수 없습니다.");
        }

        return ProfileResponseDto.builder()
            .userId(myProfile.getId())
            .name(myProfile.getName())
            .email(myProfile.getEmail())
            .password(myProfile.getPassword())
            .status(HttpStatus.OK.value())
            .build();
    }

}
