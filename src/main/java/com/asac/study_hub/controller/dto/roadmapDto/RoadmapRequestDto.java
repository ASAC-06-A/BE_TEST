package com.asac.study_hub.controller.dto.roadmapDto;

import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
public class RoadmapRequestDto {

    Integer id;
    @NotBlank
    String roadmapTitle;
    String category;
    String desc;
    LocalDateTime createAt;
    User user;

    public Roadmap to() {
        return new Roadmap(this.id, this.roadmapTitle, new Category(this.category), this.desc, this.createAt, null, this.user);
    }

}
