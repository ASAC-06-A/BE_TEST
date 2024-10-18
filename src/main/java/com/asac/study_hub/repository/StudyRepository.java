package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Study;
import com.asac.study_hub.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

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
        studyList.put(1, new Study("spring basic", "https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard", "인프런 스프링 기초 강의", user));
        studyList.put(1, new Study("spring jpa", "https://www.inflearn.com/course/ORM-JPA-Basic/dashboard", "인프런 스프링 jpa", user));
        studyList.put(1, new Study("spring data jpa", "https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%8D%B0%EC%9D%B4%ED%84%B0-JPA-%EC%8B%A4%EC%A0%84/dashboard", "인프런 스프링 data jpa", user));
        studyList.put(1, new Study("spring jpa", "https://www.inflearn.com/course/querydsl-%EC%8B%A4%EC%A0%84/dashboard", "인프런 querydsl 강의", user));
    }


}
