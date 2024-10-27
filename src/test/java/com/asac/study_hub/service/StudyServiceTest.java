package com.asac.study_hub.service;

import com.asac.study_hub.controller.dto.studyDto.StudyRequestDto;
import com.asac.study_hub.domain.Category;
import com.asac.study_hub.domain.Study;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.StudyRepository;
import com.asac.study_hub.repository.UserRepository;
import com.asac.study_hub.util.SessionProvider;
import com.mashape.unirest.http.Unirest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudyServiceTest {

    @Autowired
    private StudyService studyService;
    @Autowired
    private StudyRepository studyRepository;
    @Autowired
    private HttpServletRequest request;

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
}