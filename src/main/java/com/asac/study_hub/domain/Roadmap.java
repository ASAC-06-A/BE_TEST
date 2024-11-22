package com.asac.study_hub.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Getter
@ToString
@Entity
@NoArgsConstructor
public class Roadmap {
    //Roadmap:Study = N:M -> 별도 테이블 필요
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roadmapId;

    @Column(length = 20, nullable=false)
    private String roadmapTitle;
    @Column(length = 10)
    private String category;
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public void update(Roadmap newRoadmap) {
        this.roadmapTitle = Optional.ofNullable(newRoadmap.getRoadmapTitle()).orElse(this.roadmapTitle);
        this.category = Optional.ofNullable(newRoadmap.getCategory()).orElse(this.category);
        this.description = Optional.ofNullable(newRoadmap.getDescription()).orElse(this.description);
        this.updateAt = LocalDateTime.now();
    }

    public Roadmap(String roadmapTitle, String category, String description) {
        this.roadmapTitle = roadmapTitle;
        this.category = category;
        this.description = description;
    }
}
