package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.ResponseIdDto;
import com.asac.study_hub.controller.dto.studyDto.StudyRequestDto;
import com.asac.study_hub.controller.dto.studyDto.StudyResponseDto;
import com.asac.study_hub.domain.Study;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.asac.study_hub.repository.StudyIRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StudyService {

    StudyIRepository studyRepository;

    //모든 스터디 조회하는 메서드 구현하시면 됩니다.
    //컨트롤러에서 Dto로 받아온 Study
    /**
     * TODO: 모든 스터디 조회하는 메서드 구현
     * 아래 주석처리된 것처럼 ResponseDto 만들어서 구현해주시면 됩니다!
     */
   /* public List<StudyResponseDto> findAll() {
        List<StudyResponseDto> studyResponseDtoList = studyRepository.findAll();
        return studyResponseDtoList;
    }*/

    public StudyResponseDto findById(Integer id) {
        Study study = studyRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_STUDY_BY_ID, id));
        return StudyResponseDto.of(study);
    }

    public ResponseIdDto save(StudyRequestDto studyRequestDto) {
        //강의 제목 중복 허용
        Integer studyId = studyRepository.save(studyRequestDto.to());
        return new ResponseIdDto(studyId);
    }

}
