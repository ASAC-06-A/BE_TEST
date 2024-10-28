package com.asac.study_hub.controller;

import com.asac.study_hub.controller.dto.common.BaseResponse;
import com.asac.study_hub.controller.dto.common.SuccessType;
import com.asac.study_hub.controller.studyDto.StudyRequestDto;
import com.asac.study_hub.controller.studyDto.StudyResponseDto;
import com.asac.study_hub.service.StudyService;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    public BaseResponse<List<StudyResponseDto>> getStudy(){
       List<StudyResponseDto> studyResponseDto = studyService.findAll();
       return BaseResponse.success(SuccessType.FINDALL, studyResponseDto);
    }
}
