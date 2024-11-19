package com.asac.study_hub.controller.dto.studyDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
public class StudyIdRequestDto {
    @Getter
    @NotNull
    List<Integer> studyId;
}
