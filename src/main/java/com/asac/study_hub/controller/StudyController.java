package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.ListResponseDto;
import com.asac.study_hub.controller.dto.ResponseIdDto;
import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.controller.dto.studyDto.StudyRequestDto;
import com.asac.study_hub.controller.dto.studyDto.StudyResponseDto;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.service.StudyService;
import com.asac.study_hub.util.SessionProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/study")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class StudyController {

    StudyService studyService;
    /**
     * TODO: UserController 참고하면서 스터디 조회 컨트롤러 구현
     * 아래 주석처리된 컨트롤러처럼 구현하시면 됩니다!
     */
   /* public BaseResponse<*//*List<StudyReqeustDto>*//*> getStudy() {
        StudyResponseDto studyResponseDto = studyService.findAll();
        return BaseResponse.success(SuccessType.GET_ALL_STUDY, studyResponseDto);
    }*/

    @GetMapping("/{id}")
    public BaseResponse<StudyResponseDto> getStudy(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request, @Valid @PathVariable Integer id) {
        SessionProvider.getValidUser(cookie.getValue(), request);
        StudyResponseDto studyResponseDto = studyService.findById(id);
        return BaseResponse.success(SuccessType.GET_STUDY, studyResponseDto);
    }

    @PostMapping
    public BaseResponse<ResponseIdDto> save(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request, @Valid @RequestBody StudyRequestDto studyRequestDto) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        ResponseIdDto studyId = studyService.save(user, studyRequestDto);
        return BaseResponse.success(SuccessType.SAVE_STUDY, studyId);
    }

    @GetMapping
    public BaseResponse<ListResponseDto<StudyResponseDto>> getStudyByCategory(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request, @RequestParam String category) {
        SessionProvider.getValidSession(request);
        return BaseResponse.success(SuccessType.GET_STUDY_BY_CATEGORY, studyService.getStudyByCategory(category));
    }

    //부분 수정
    @PatchMapping("/{id}")
    public BaseResponse<StudyResponseDto> update(@CookieValue("JSESSIONID") Cookie cookie, HttpServletRequest request, @PathVariable Integer id, @Valid @RequestBody StudyRequestDto studyRequestDto){
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        studyService.update(id, studyRequestDto, user);
        return BaseResponse.success(SuccessType.UPDATE_SUCCESS, null);
    }

    @PostMapping("/delete")
    public BaseResponse<Void> deleteAll(@CookieValue Cookie cookie, HttpServletRequest request, @RequestBody List<Integer> id) {
        User user = SessionProvider.getValidUser(cookie.getValue(), request);
        studyService.deleteAll(user, id);
        return BaseResponse.success(SuccessType.DELETE_ALL_SUCCESS, null);
    }

  @GetMapping("/studies")
  public BaseResponse<List<com.asac.study_hub.controller.studyDto.StudyResponseDto>> getStudy() {
    return BaseResponse.success(SuccessType.FINDALL, studyService.findAll());
  }
}
