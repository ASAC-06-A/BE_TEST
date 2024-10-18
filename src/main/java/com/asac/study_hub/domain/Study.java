package com.asac.study_hub.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Study {
    String title;
    String studyLink;
    String description;
    User user; //FK
}
