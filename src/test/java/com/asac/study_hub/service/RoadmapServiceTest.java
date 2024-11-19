package com.asac.study_hub.service;

import com.asac.study_hub.domain.*;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.asac.study_hub.repository.RoadmapRepository;
import com.asac.study_hub.repository.StudyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class RoadmapServiceTest {
    @Autowired
    private RoadmapRepository roadmapRepository;
    @Autowired
    private StudyRepository studyRepository;
    @Autowired
    private RoadmapService roadmapService;

    @DisplayName("로드맵 삭제 권한 확인")
    @Test
    void delete() {
        //given
        User validUser = new User(100, "validUser", "vlidmail@gmail.com", "123456789", Status.ACTIVE);
        User inValidUser = new User(101, "notValidUser", "notvlidmail@gmail.com", "123456789", Status.ACTIVE);
        User inactiveUser= new User(101, "notValidUser", "notvlidmail@gmail.com", "123456789", Status.INACTIVE);
        Study study = Study.builder()
                .id(100) //인메모리 저장 방식이라 강의 다수 삭제에서 스터디 id 10이랑 겹치면서 다수 삭제 test에서 id = 10인 스터디 삭제해도 이 데이터가 메모리에 남아 있으므로 예외가 터지지 않음
                .category(new Category("Backend"))
                .title("spring basic1")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(validUser)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .order(1)
                .build();
        studyRepository.save(study);
        roadmapRepository.save(new Roadmap(1, "new roadmap", new Category("Backend"), "desc", LocalDateTime.now(), LocalDateTime.now(), validUser));

        Study study2 = Study.builder()
                .id(101) //인메모리 저장 방식이라 강의 다수 삭제에서 스터디 id 10이랑 겹치면서 다수 삭제 test에서 id = 10인 스터디 삭제해도 이 데이터가 메모리에 남아 있으므로 예외가 터지지 않음
                .category(new Category("Backend"))
                .title("spring basic1")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(inactiveUser)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .order(1)
                .build();
        studyRepository.save(study2);
        roadmapRepository.save(new Roadmap(2, "new roadmap", new Category("Backend"),"desc", LocalDateTime.now(), LocalDateTime.now(), inactiveUser));


        //when

        //then
        Assertions.assertThatThrownBy(() -> roadmapService.delete(inValidUser, 1))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("type", ExceptionType.INVALID_AUTHORIZATION);

        Assertions.assertThatThrownBy(() -> roadmapService.delete(inactiveUser, 2))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("type", ExceptionType.INVALID_AUTHORIZATION);

    }
}