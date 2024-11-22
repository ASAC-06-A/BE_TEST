package com.asac.study_hub.service.dbService;

import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.IRepository;
import com.asac.study_hub.repository.dbRepository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;
}
