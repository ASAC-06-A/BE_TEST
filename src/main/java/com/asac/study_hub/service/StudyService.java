package com.asac.study_hub.service;

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

}
