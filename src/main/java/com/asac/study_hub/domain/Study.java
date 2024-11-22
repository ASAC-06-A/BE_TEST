package com.asac.study_hub.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder //HashMap 에 객체 초기화하기 위함.
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Study {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studyId;

    @Column(length=20, nullable=false)
    private String studyTitle;

    private String studyLink;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(length = 10)
    private String category;

    @Column(columnDefinition = "int default 0")
    private Integer likes;

    @Column(columnDefinition = "enum ('DONE','IN_PROGRESS','TODO') default 'TODO'")
    @Enumerated(EnumType.STRING)
    private StudyStatus studyStatus;

    @Column(columnDefinition = "int default 0")
    private Integer progress = 0; // 깅의 진행 퍼센테이지

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable=false)
    private String studyImg;
    private Integer studyOrder;

    public void update(Study newStudy) {
        this.studyTitle = newStudy.getStudyTitle() != null ? newStudy.getStudyTitle() : this.studyTitle;
        this.studyLink = newStudy.getStudyLink()!= null ? newStudy.getStudyLink() : this.studyLink;
        this.category = newStudy.getCategory()!= null ? newStudy.getCategory() : this.category;
        this.description = newStudy.getDescription()!= null ? newStudy.getDescription() : this.description;
        this.studyStatus = newStudy.getStudyStatus()!= null ? newStudy.getStudyStatus() : this.studyStatus;
        this.updateAt = LocalDateTime.now();
        this.studyOrder = newStudy.getStudyOrder()!= null ? newStudy.getStudyOrder() : this.studyOrder;

    }
}
