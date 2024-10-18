package com.asac.study_hub.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum StudyStatus {
    TODO, IN_PROGRESS, DONE
}
