package com.asac.study_hub.controller;

import com.asac.study_hub.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@Slf4j
public class SessionController {

  //@SessionAttribute 는 나중에 jwt를 사용하게 된다면 jwt 검증 어노테이션을 사용할 것임 -> test Controller 입니다. 삭제하셔도돼요
  @GetMapping("/session")
  public User test(@SessionAttribute(name = "session_key") User user) {
    log.info(user.toString());
    return user;

  }
}
