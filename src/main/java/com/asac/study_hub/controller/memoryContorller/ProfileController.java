package com.asac.study_hub.controller.memoryContorller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.controller.dto.profileDto.ProfileResponseDto;
import com.asac.study_hub.controller.dto.profileDto.ProfileUpdateRequestDto;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.service.memoryService.ProfileService;
import com.asac.study_hub.util.SessionProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

//@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/profile")
@Slf4j
public class ProfileController {

    ProfileService profileService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public BaseResponse<ProfileResponseDto> getProfile(@CookieValue("JSESSIONID") Cookie cookie,
        HttpServletRequest request) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        return BaseResponse.success(SuccessType.GET_PROFILE, ProfileResponseDto.of(user));
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PatchMapping
    public BaseResponse<ProfileResponseDto> updateProfile(@CookieValue("JSESSIONID") Cookie cookie,
        HttpServletRequest request,
        @Valid @RequestBody ProfileUpdateRequestDto requestDto) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
//        profileService.updateUser(user, requestDto);
        return BaseResponse.success(SuccessType.UPDATE_PROFILE_SUCCESS, null);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @DeleteMapping
    public BaseResponse<Void> deleteProfile(@CookieValue("JSESSIONID") Cookie cookie,
        HttpServletRequest request) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
//        profileService.deleteUser(user);
        return BaseResponse.success(SuccessType.DELETE_PROFILE, null);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/logout") // 상태를 변경하기에 PostMapping or Session을 제거하니 DeleteMapping
    public BaseResponse<Void> logoutProfile(@CookieValue("JSESSIONID") Cookie cookie,
        HttpServletRequest request) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        //profileService.logoutUser(user);
        SessionProvider.removeSession(request);
        return BaseResponse.success(SuccessType.LOGOUT_PROFILE, null);
    }

}
