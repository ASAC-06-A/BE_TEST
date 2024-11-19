package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.ListResponseDto;
import com.asac.study_hub.controller.dto.studyDto.StudyIdRequestDto;
import com.asac.study_hub.controller.dto.studyDto.StudyResponseDto;
import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.domain.RoadmapStudy;
import com.asac.study_hub.domain.Study;
import com.asac.study_hub.repository.RoadmapStudyRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RoadmapStudyService {

    RoadmapStudyRepository roadmapStudyRepository;

    //Roadmap과 연관된 Study 가져와서 Dto로 변환
    public ListResponseDto<StudyResponseDto> findStudyByRoadmap(Roadmap roadmap) {
        List<RoadmapStudy> roadmapStidyList = roadmapStudyRepository.findByRoadmap(roadmap);
        //study -> studyDto
        List<StudyResponseDto> studyResponseDto = roadmapStidyList.stream()
                .map(each -> StudyResponseDto.of(each.getStudy()))
                .toList();
        ListResponseDto<StudyResponseDto> responseDto = new ListResponseDto<>(studyResponseDto.size(), studyResponseDto);
        return responseDto;
    }

    public void saveStudyToRoadmap(Roadmap roadmap, List<Study> studyList) {
        studyList.forEach(study -> roadmapStudyRepository.save(new RoadmapStudy(roadmap, study)));
    }
}
