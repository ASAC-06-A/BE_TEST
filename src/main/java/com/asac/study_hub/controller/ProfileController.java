package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.controller.dto.profileDto.ProfileResponseDto;
import com.asac.study_hub.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/profile")
@Slf4j
public class ProfileController {

    ProfileService profileService;

    @GetMapping("/")
    public BaseResponse<ProfileResponseDto> getProfile(HttpServletRequest request){
        ProfileResponseDto profileResponseDto = profileService.getMyProfile(request);
        return BaseResponse.success(SuccessType.GETPROFILE, profileResponseDto);
    }

}
