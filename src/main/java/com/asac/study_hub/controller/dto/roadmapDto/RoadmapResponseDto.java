package com.asac.study_hub.controller.dto.roadmapDto;

import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.repository.RoadmapRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RoadmapResponseDto {
    Integer roadmapId;
    String roadmapTitle;
    String category;
    LocalDateTime createAt;

    public static RoadmapResponseDto to(Roadmap roadmap) {
        return new RoadmapResponseDto(roadmap.getId(), roadmap.getRoadmapTitle(), roadmap.getCategory().getCategory(), roadmap.getCreateAt());
    }
}
