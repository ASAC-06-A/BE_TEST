package com.asac.study_hub.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StudyIdListRequestDto {
    List<Integer> studyIdList;
}
