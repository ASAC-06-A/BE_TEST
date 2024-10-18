package com.asac.study_hub.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Study {
    Integer id;
    String title;
    String studyLink;
    String description;
    Category category;
    @Builder.Default
    Integer likes = 0;
    @Builder.Default
    StudyStatus studyStatus = StudyStatus.TODO; //강의 상태
    @Builder.Default
    Integer progress = 0; // 깅의 진행 퍼센테이지
    LocalDateTime createAt;
    LocalDateTime updateAt;
    @Builder.Default
    Status status = Status.ACTIVE;
    User user; //FK
}
