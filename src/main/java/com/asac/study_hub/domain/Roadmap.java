package com.asac.study_hub.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Roadmap {
    //Roadmap:Study = N:M -> 별도 테이블 필요
    Integer id;
    String roadmapTitle;
    Category category;
    LocalDateTime createAt;
    LocalDateTime updateAt;
    User user; //FK
}
