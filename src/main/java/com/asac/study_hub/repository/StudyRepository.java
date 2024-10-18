package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Study;
import com.asac.study_hub.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class StudyRepository {
    /**
     * TODO: StudyIRepository 구현
     * findAll() 메서드 구현: studyList 해시앱에 있는 value 값 List 로 반환
     */

    Study study;
    static final HashMap<Integer, Study> studyList;

    static {
        studyList = new HashMap<>();
        User user = new User(1, "jiyeon", "jiyeon@gmail.com", "jiyeon7890");
        studyList.put(1, Study.builder()
                .id(1)
                .category(new Category("Backend"))
                .title("spring basic1")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(user)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build());
        studyList.put(2, Study.builder()
                .id(2)
                .category(new Category("Backend"))
                .title("spring basic2")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(user)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build());
        studyList.put(3, Study.builder()
                .id(1)
                .category(new Category("Backend"))
                .title("spring basic3")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(user)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build());

    }


}
