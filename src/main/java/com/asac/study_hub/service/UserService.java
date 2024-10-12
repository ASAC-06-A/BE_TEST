package com.asac.study_hub.service;

import com.asac.study_hub.domain.User;
import com.asac.study_hub.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;

    private static final HashMap<Integer, User> users;
    static {
        users = new HashMap<>();
        users.put(1, new User(1L, "김정현", "solee3020@gmail.com", "solee6810"));
        users.put(2, new User(2L, "김지연", "jykim9335@gmail.com", "asas5656"));
    }

}
