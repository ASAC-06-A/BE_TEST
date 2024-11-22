package com.asac.study_hub.controller.dto.studyDto;

import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Study;
import com.asac.study_hub.domain.StudyStatus;
import com.asac.study_hub.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class StudyRequestDto {
    @Setter
    Integer studyId;
    @NotBlank
    String studyTitle;
    @NotNull
    String url;
    String desc;
    String category;
    @Setter
    User user;
    Integer order;
    StudyStatus studyStatus;

    public Study to() {
        return Study.builder()
                .studyId(this.studyId)
                .studyTitle(this.studyTitle)
                .studyLink(this.url)
                .description(this.desc)
                .category(this.category)
                .createAt(LocalDateTime.now())
                .user(this.user)
                .studyOrder(this.order)
                .build();
    }
}
