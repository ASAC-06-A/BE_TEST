package com.asac.study_hub.controller.dto.roadmapDto;

import com.asac.study_hub.controller.dto.ListResponseDto;
import com.asac.study_hub.controller.dto.studyDto.StudyResponseDto;
import com.asac.study_hub.domain.Roadmap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class RoadmapResponseDto {
    Integer roadmapId;
    String roadmapTitle;
    String category;
    String desc;
    LocalDateTime createAt;
    @Setter
    ListResponseDto<StudyResponseDto> study;

    public static RoadmapResponseDto to(Roadmap roadmap) {
        return new RoadmapResponseDto(roadmap.getRoadmapId(), roadmap.getRoadmapTitle(), roadmap.getCategory(), roadmap.getDescription(), roadmap.getCreateAt());
    }

    public RoadmapResponseDto(Integer roadmapId, String roadmapTitle, String category, String desc, LocalDateTime createAt) {
        this.roadmapId = roadmapId;
        this.roadmapTitle = roadmapTitle;
        this.category = category;
        this.createAt = createAt;
        this.desc = desc;
    }
}
