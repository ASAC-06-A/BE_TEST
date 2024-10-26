package com.asac.study_hub.controller.dto.studyDto;

import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Study;
import com.asac.study_hub.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class StudyRequestDto {
    @Setter
    Integer studyId;
    @NotBlank
    String studyTitle;
    @NotNull
    String url;
    String desc;
    Category category;
    LocalDateTime createAt;
    User user;
    Integer order;

    public Study to() {
        return Study.builder()
                .title(this.studyTitle)
                .studyLink(this.url)
                .description(this.desc)
                .category(this.category)
                .createAt(LocalDateTime.now())
                .user(this.user)
                .order(this.order)
                .build();
    }
}
