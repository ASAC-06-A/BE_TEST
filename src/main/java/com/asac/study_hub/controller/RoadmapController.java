package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.controller.dto.roadmapDto.RoadmapResponseDto;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.service.RoadmapService;
import com.asac.study_hub.util.SessionProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/roadmap")
@RequiredArgsConstructor
public class RoadmapController {
    RoadmapService roadmapService;

    @GetMapping
    public BaseResponse<List<RoadmapResponseDto>> findAll(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        List<RoadmapResponseDto> responseDto = roadmapService.findAll(user);
        return BaseResponse.success(SuccessType.GET_ALL_ROADMAP, responseDto);
    }

    @DeleteMapping({"roadmapId"})
    public BaseResponse<Void> delete(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request, @PathVariable Integer roadmapId) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        roadmapService.delete(user, roadmapId);
        return BaseResponse.success(SuccessType.DELETE_ROADMAP, null);
    }

}
