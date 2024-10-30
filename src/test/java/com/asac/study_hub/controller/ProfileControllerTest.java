package com.asac.study_hub.controller;

import com.asac.study_hub.controller.ProfileController;
import com.asac.study_hub.controller.dto.profileDto.ProfileResponseDto;
import com.asac.study_hub.domain.Status;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockCookie;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.MvcResult;



@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        User mockUser = new User(1, "김정현", "solee3013@gmail.com", "vkdls12^^", Status.ACTIVE);

        when(userRepository.searchMyProfile(anyString())).thenReturn(mockUser);
    }

//    @Test
    public void getProfileWithSessionCookie() throws Exception {
        MockCookie sessionCookie = new MockCookie("session_key", "validSessionId"); // 쿠키 설정

        // 테스트 실행 후 결과 저장
        MvcResult result = mockMvc.perform(get("/profile/")
                .cookie(sessionCookie) // 쿠키 추가
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()) // 예상되는 상태 코드 확인
            .andExpect(jsonPath("$.body.userId").value(1))
            .andExpect(jsonPath("$.body.name").value("김정현"))
            .andExpect(jsonPath("$.body.email").value("solee3013@gmail.com"))
            .andReturn();

        // 응답 본문 출력
        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Response Content: " + responseContent);
    }
}