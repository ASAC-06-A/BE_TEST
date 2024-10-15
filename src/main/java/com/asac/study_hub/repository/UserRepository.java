package com.asac.study_hub.repository;

import com.asac.study_hub.controller.dto.userDto.signupDto.SignupRequestDto;
import com.asac.study_hub.domain.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRepository implements IRepository {
    User user;

    private static final HashMap<Integer, User> users;
    private static final HashMap<String, User> sessionStorage;
    static {
        users = new HashMap<>();
        users.put(1, new User(1, "김정현", "solee3020@gmail.com", "solee6810"));
        users.put(2, new User(2, "김지연", "jykim9335@gmail.com", "asas5656"));

        sessionStorage = new HashMap<>();
    }

    public User save(User user) {
        users.put(users.size()+1, user);
        return user;
    }

    public String saveSession(String sessionId, User user) {
        sessionStorage.put(sessionId, user); //repository로 옮기기
        return sessionId;
    }

    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    public List<User> findAll() {
        return List.of();
    }

    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
