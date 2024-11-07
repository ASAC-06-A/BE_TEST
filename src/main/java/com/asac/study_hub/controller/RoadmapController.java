package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.ListResponseDto;
import com.asac.study_hub.controller.dto.ResponseIdDto;
import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.controller.dto.roadmapDto.RoadmapRequestDto;
import com.asac.study_hub.controller.dto.roadmapDto.RoadmapResponseDto;
import com.asac.study_hub.controller.dto.studyDto.StudyIdRequestDto;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.service.RoadmapService;
import com.asac.study_hub.util.SessionProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/roadmap")
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoadmapController {
    RoadmapService roadmapService;

    @GetMapping
    public BaseResponse<ListResponseDto<RoadmapResponseDto>> findAll(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        ListResponseDto<RoadmapResponseDto> responseDto = roadmapService.findAll(user);
        return BaseResponse.success(SuccessType.GET_ALL_ROADMAP, responseDto);
    }

    @GetMapping("/{roadmapId}")
    public BaseResponse<RoadmapResponseDto> findById(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request, @PathVariable Integer roadmapId) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        RoadmapResponseDto roadmapResponseDto = roadmapService.findById(user, roadmapId);
        return BaseResponse.success(SuccessType.GET_ROADMAP, roadmapResponseDto);
    }

    @DeleteMapping("/{roadmapId}")
    public BaseResponse<Void> delete(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request, @PathVariable Integer roadmapId) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        roadmapService.delete(user, roadmapId);
        return BaseResponse.success(SuccessType.DELETE_ROADMAP, null);
    }

    @PostMapping
    public BaseResponse<ResponseIdDto> save(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request, @Valid @RequestBody RoadmapRequestDto roadmapRequestDto) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        ResponseIdDto id = roadmapService.save(user, roadmapRequestDto);
        return BaseResponse.success(SuccessType.CREATE, id);
    }

    @PostMapping("/{roadmapId}/study")
    public BaseResponse<Void> saveStudy(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request, @Valid @RequestBody StudyIdRequestDto studyIdList, @PathVariable Integer roadmapId) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        roadmapService.saveAllStudy(user, roadmapId, studyIdList);
        return BaseResponse.success(SuccessType.SAVE_STUDY_TO_ROADMAP, null);
    }

}
