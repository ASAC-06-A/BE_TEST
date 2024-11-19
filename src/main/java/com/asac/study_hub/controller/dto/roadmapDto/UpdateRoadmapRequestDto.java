package com.asac.study_hub.controller.dto.roadmapDto;

import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Roadmap;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
@Setter
public class UpdateRoadmapRequestDto {
    private String roadmapTitle;
    private String category;
    private String desc;

    public Roadmap to() {
        return new Roadmap(this.roadmapTitle, new Category(this.category), this.desc);
    }
}
