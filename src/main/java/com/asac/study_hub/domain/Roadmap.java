package com.asac.study_hub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Getter
@ToString
public class Roadmap {
    //Roadmap:Study = N:M -> 별도 테이블 필요
    @Setter
    Integer id;
    String roadmapTitle;
    Category category;
    String description;
    LocalDateTime createAt;
    LocalDateTime updateAt;
    User user; //FK

    public void update(Roadmap newRoadmap) {
        this.roadmapTitle = Optional.ofNullable(newRoadmap.getRoadmapTitle()).orElse(this.roadmapTitle);
        this.category = Optional.ofNullable(newRoadmap.getCategory()).orElse(this.category);
        this.description = Optional.ofNullable(newRoadmap.getDescription()).orElse(this.description);
        this.updateAt = LocalDateTime.now();
    }

    public Roadmap(String roadmapTitle, Category category, String description) {
        this.roadmapTitle = roadmapTitle;
        this.category = category;
        this.description = description;
    }
}
