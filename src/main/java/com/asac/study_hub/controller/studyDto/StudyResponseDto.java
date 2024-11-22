package com.asac.study_hub.controller.studyDto;

import com.asac.study_hub.domain.Study;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


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
    return new StudyResponseDto(study.getStudyId(),study.getStudyImg(), study.getStudyTitle(), study.getStudyLink(), study.getDescription(), study.getCategory(), study.getCreateAt(), study.getStudyStatus().name());
  }
}