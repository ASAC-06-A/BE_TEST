package com.asac.study_hub.util;

import com.asac.study_hub.domain.UserStatus;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import java.util.logging.Logger;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;


@SpringBootTest
class SessionProviderTest {

    @DisplayName("세션에 user 객체 저장")
    @Test
    void createSession() {
        //given
        User user = new User(10, "김지연", "example@gmail.com", "1234", UserStatus.ACTIVE);
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/study");

        //when
        //login 후 세션이 생성된후 세션을 get할때 user가 제대로 들어가 있는지
        HttpSession session = SessionProvider.createSession(req, user);

        //then
        Assertions.assertThat(SessionProvider.getValidUser(session.getId(), req)).isEqualTo(user);
    }

    @DisplayName("만료된 세션으로 요청서 CustomException 발생")
    @Test
    void expiredSession() throws InterruptedException {
        //given
        User user = new User(10, "김지연", "example@gmail.com", "1234", UserStatus.ACTIVE);
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/study");
        HttpSession session = SessionProvider.createSession(req, user);
        String sessionId = session.getId(); //탈취된 세션으로 만료시간 후 요청을 보냈을 때 예외 발생
        //when
        session.invalidate(); //명시적으로 세션 만료후 예외 발생하는지 확인

        //then
        Assertions.assertThatThrownBy(() -> SessionProvider.getValidSession(req))
            .isInstanceOf(CustomException.class)
            .hasFieldOrPropertyWithValue("type", ExceptionType.EXPIRED_SESSION);

    }

    @DisplayName("유효하지 않는 세션으로 요청시 세션에 있는 User 객체 가져오지 못하고 CustomException 발생")
    @Test
    void invalidSession() {
        //given
        String sessionId = UUID.randomUUID()
            .toString(); //HttpSession 이 생성하는 sessionId와 다른 방식으로 생성된 id
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/study");

        //when
        req.getSession(); //세션이 생성
        //then
        Assertions.assertThatThrownBy(() -> SessionProvider.getValidUser(sessionId, req))
            .isInstanceOf(CustomException.class)
            .hasFieldOrPropertyWithValue("type", ExceptionType.INVALID_SESSION);


    }

    private static final Logger logger = Logger.getLogger(SessionProviderTest.class.getName());

    @DisplayName("세션에서 user 객체 삭제")
    @Test
    void removeSession() {
        // given: 테스트 준비 단계
        User user = new User(10, "김지연", "example@gmail.com", "1234", UserStatus.ACTIVE);
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/study");

        // 세션 생성 및 user 저장
        HttpSession session = SessionProvider.createSession(req, user); //req

        String sessionId = session.getId();
        //String newSessionId = newSession.getId();
        if (!(session.getAttribute(sessionId) == null)) {
            logger.info("세션이 정상적으로 생성되었습니다.");
            logger.info(
                "세션 ID: " + sessionId + ", user객체: " + session.getAttribute(sessionId));
        } else {
            logger.info("세션 생성실패");
        }

        // when: 테스트 대상 메서드 실행 (user를 세션에서 삭제)
        SessionProvider.removeSession(/*sessionId,*/ req);

        // then: 기대 결과 검증 (세션에서 user가 제거되었는지 확인)
        Assertions.assertThatThrownBy(() -> SessionProvider.getValidUser(sessionId, req))
            .isInstanceOf(CustomException.class)
            .hasMessageContaining(ExceptionType.EXPIRED_SESSION.getMessage());

        // removeSession의 첫줄에서 HttpSession session = getValidSession(request);
        // 세션이 비어있으면 getValidSession부분에서 EXPIRED_SESSION가 뜸
    }
}