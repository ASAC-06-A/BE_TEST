package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.studyDto.StudyRequestDto;
import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Study;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import com.asac.study_hub.repository.StudyRepository;
import com.asac.study_hub.util.SessionProvider;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StudyServiceTest {

    @Autowired
    private StudyService studyService;
    @Autowired
    private StudyRepository studyRepository;

    @DisplayName("강의 부분 수정")
    @Test
    void update() {
        //given
        User user = new User(10, "김지연", "example@gmail.com", "1234");
        Study study = Study.builder()
                .id(10)
                .category(new Category("Backend"))
                .title("spring basic1")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(user)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .order(1)
                .build();
        studyRepository.save(study);

        //when
        MockHttpServletRequest req = new MockHttpServletRequest("PATCH", "/study/10");
        HttpSession session = SessionProvider.createSession(req, user);
        Integer studyId = 10;
        StudyRequestDto studyRequestDto = new StudyRequestDto(10, "수정", "http://localhost:8080/study/1", "desc", null, null, null, null, null);
        studyService.update(studyId, studyRequestDto, user);
        //then
        Assertions.assertThat(study.getTitle()).isEqualTo(studyRequestDto.getStudyTitle());
        Assertions.assertThat(study.getStudyLink()).isEqualTo(studyRequestDto.getUrl());
        Assertions.assertThat(study.getDescription()).isEqualTo(studyRequestDto.getDesc());
        Assertions.assertThat(study.getCategory().getCategory()).isEqualTo("Backend");
        Assertions.assertThat(study.getOrder()).isEqualTo(1);
        Assertions.assertThat(study.getCategory().getCategory()).isEqualTo("Backend");
    }

    @DisplayName("강의 다수 삭제")
    @Test
    void deleteAll() {
        //given
        User user = new User(10, "김지연", "example@gmail.com", "1234");
        Study study1 = Study.builder()
                .id(10)
                .category(new Category("Backend"))
                .title("spring basic1")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(user)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .order(1)
                .build();
        studyRepository.save(study1);

        Study study2 = Study.builder()
                .id(11)
                .category(new Category("Backend"))
                .title("spring basic1")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(user)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .order(1)
                .build();
        studyRepository.save(study2);
        Study study3 = Study.builder()
                .id(13)
                .category(new Category("Backend"))
                .title("spring basic1")
                .studyLink("https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard")
                .description("인프런 스프링 기초 강의")
                .user(user)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .order(1)
                .build();
        Integer id = studyRepository.save(study3); //가장 마지막에 추가 == studyRepository.getId()와 동일


        List<Integer> ids = new ArrayList<>();
        ids.add(10);
        ids.add(11);

        //when
        studyService.deleteAll(user, ids);

        //then
        Assertions.assertThatThrownBy(() -> studyService.findById(10))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("type", ExceptionType.NOT_FOUND_STUDY_BY_ID);

        Assertions.assertThatThrownBy(() -> studyService.findById(11))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("type", ExceptionType.NOT_FOUND_STUDY_BY_ID);

        Assertions.assertThat(id)
                .isNotEqualTo(studyRepository.getId());

    }
}