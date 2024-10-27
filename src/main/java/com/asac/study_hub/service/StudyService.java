package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.ListResponseDto;
import com.asac.study_hub.controller.dto.ResponseIdDto;
import com.asac.study_hub.controller.dto.studyDto.StudyRequestDto;
import com.asac.study_hub.controller.dto.studyDto.StudyResponseDto;
import com.asac.study_hub.domain.Study;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.asac.study_hub.repository.StudyIRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseIdDto save(User user, StudyRequestDto studyRequestDto) {
        //강의 제목 중복 허용
        studyRequestDto.setUser(user);
        Integer studyId = studyRepository.save(studyRequestDto.to());
        return new ResponseIdDto(studyId);
    }

    public ListResponseDto<StudyResponseDto> getStudyByCategory(String category) {

        List<StudyResponseDto> studyList = studyRepository.findByCategory(category).stream()
                .map(StudyResponseDto::of)
                .toList();

        return new ListResponseDto<StudyResponseDto>(studyList.size(), studyList);
    }

    public void update(Integer id, StudyRequestDto studyRequestDto, User user) {
        Study study = studyRepository.findById(id).orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUND_STUDY_BY_ID));

        if (!checkAuthorization(user, study)) {
            throw new CustomException(ExceptionType.INVALID_AUTHORIZATION);
        }

        Study newStudy = studyRequestDto.to();
        studyRepository.update(study, newStudy);
    }

    private boolean checkAuthorization(User user, Study study) {
        //수정할 권한이 있는지 확인
        return study.getUser().equals(user);
    }
}
