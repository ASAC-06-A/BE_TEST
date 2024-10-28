package com.asac.study_hub.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder //HashMap 에 객체 초기화하기 위함.
@Getter
public class Study {
    Integer id;
    String title;
    String studyLink;
    String description;
    @Builder.Default
    Category category = new Category("None"); //FK
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
    String studyImgUrl;
    Integer order;

    public void update(Study newStudy) {
        this.title = newStudy.getTitle() != null ? newStudy.getTitle() : this.title;
        this.studyLink = newStudy.getStudyLink()!= null ? newStudy.getStudyLink() : this.studyLink;
        this.category = newStudy.getCategory()!= null ? newStudy.getCategory() : this.category;
        this.description = newStudy.getDescription()!= null ? newStudy.getDescription() : this.description;
        this.studyStatus = newStudy.getStudyStatus()!= null ? newStudy.getStudyStatus() : this.studyStatus;
        this.updateAt = LocalDateTime.now();
        this.order = newStudy.getOrder()!= null ? newStudy.getOrder() : this.order;

    }
}
