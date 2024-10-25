package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.controller.dto.studyDto.StudyResponseDto;
import com.asac.study_hub.controller.dto.userDto.signupDto.SignupResponseDto;
import com.asac.study_hub.service.StudyService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse<StudyResponseDto> getStudy(@Valid @PathVariable Integer id) {
        StudyResponseDto studyResponseDto = studyService.findById(id);
        return BaseResponse.success(SuccessType.GET_STUDY, studyResponseDto);
    }
}
