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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/profile")
@Slf4j
public class ProfileController {

    ProfileService profileService;

    @GetMapping("")
    public BaseResponse<ProfileResponseDto> getProfile(@CookieValue("JSESSIONID") Cookie cookie,
        HttpServletRequest request) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        return BaseResponse.success(SuccessType.GET_PROFILE, ProfileResponseDto.of(user));
    }

//    @PatchMapping("/{id}")
//    public BaseResponse<ProfileResponseDto> update(@CookieValue("session_key") Cookie cookie, HttpServletRequest request, @PathVariable Integer id, @Value @RequestBody
//        ProfileRequestDto profileRequestDto){
//        User user =
//        profileService.updateUser(id, profileRequestDto, user);
//        return BaseResponse.success(SuccessType.UPDATE_PROFILE_SUCCESS, null);
//    }

    // abstract BaseResponse<ProfileResponseDto> deleteUser()

    @PatchMapping("/${id}")
    public BaseResponse<ProfileResponseDto> updateProfile(@CookieValue("JSESSIONID") Cookie cookie,
        HttpServletRequest request, @PathVariable Integer id, @Valid @RequestBody
    ProfileRequestDto profileRequestDto) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        profileService.updateUser(user, profileRequestDto);
        return BaseResponse.success(SuccessType.UPDATE_PROFILE_SUCCESS, null);
    }

    @DeleteMapping("")
    public BaseResponse<Void> deleteProfile(@CookieValue("JSESSIONID") Cookie cookie,
        HttpServletRequest request) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        profileService.deleteUser(user);
    }

}
