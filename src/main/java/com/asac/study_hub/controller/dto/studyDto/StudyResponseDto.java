package com.asac.study_hub.controller.dto.studyDto;

import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Study;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StudyResponseDto {

    Integer studyId;
    String studyImg;
    String studyTitle;
    String url;
    String desc;
    String category;
    LocalDateTime createAt;
    String studyStatus;

    public static StudyResponseDto of(Study study) {
        return new StudyResponseDto(study.getId(), study.getStudyImgUrl(), study.getTitle(), study.getStudyLink(), study.getDescription(), study.getCategory().getCategory(), study.getCreateAt(), study.getStudyStatus().name());
    }
}
