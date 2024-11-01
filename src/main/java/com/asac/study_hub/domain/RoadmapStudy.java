package com.asac.study_hub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RoadmapStudy {
    Integer id;
    Study study;
    Roadmap roadmap;
}
