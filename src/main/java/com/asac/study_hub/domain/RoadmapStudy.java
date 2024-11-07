package com.asac.study_hub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class RoadmapStudy {
    @Setter
    Integer id;
    Study study;
    Roadmap roadmap;

    public RoadmapStudy(Roadmap roadmap, Study study) {
        this.roadmap = roadmap;
        this.study = study;
    }
}
