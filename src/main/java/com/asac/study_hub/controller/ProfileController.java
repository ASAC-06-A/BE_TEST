package com.asac.study_hub.controller;

import com.asac.study_hub.service.dbService.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/profile")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProfileController {

    ProfileService profileService;
}
