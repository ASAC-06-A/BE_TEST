package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.controller.dto.profileDto.ProfileRequestDto;
import com.asac.study_hub.controller.dto.profileDto.ProfileResponseDto;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.service.ProfileService;
import com.asac.study_hub.util.SessionProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/profile")
@Slf4j
public class ProfileController {

    ProfileService profileService;

    @GetMapping
    public BaseResponse<ProfileResponseDto> getProfile(@CookieValue("JSESSIONID") Cookie cookie,
        HttpServletRequest request) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        return BaseResponse.success(SuccessType.GET_PROFILE, ProfileResponseDto.of(user));
    }

    @PatchMapping
    public BaseResponse<ProfileResponseDto> updateProfile(@CookieValue("JSESSIONID") Cookie cookie,
        HttpServletRequest request, @Valid @RequestBody ProfileRequestDto profileRequestDto) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        profileService.updateUser(user, profileRequestDto);
        return BaseResponse.success(SuccessType.UPDATE_PROFILE_SUCCESS, null); //업데이터 된 정보 넘겨주는게 나은지 아니면 응답 성공 여부만 넘겨주는게 나은지
    }

    @DeleteMapping
    public BaseResponse<Void> deleteProfile(@CookieValue("JSESSIONID") Cookie cookie,
        HttpServletRequest request) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        profileService.deleteUser(user);
        return BaseResponse.success(SuccessType.DELETE_PROFILE, null);
    }

    @PostMapping("/logout") //로그아웃은 get 또는 post로 하는데 post로 하는게 좋다고 함. 이유는 찾아보기
    public BaseResponse<Void> logoutProfile(/*@CookieValue("JSESSIONID") Cookie cookie,*/
        HttpServletRequest request) {
        SessionProvider.removeSession(/*cookie.getValue(),*/ request);
        return BaseResponse.success(SuccessType.LOGOUT_PROFILE, null);
    }


}
