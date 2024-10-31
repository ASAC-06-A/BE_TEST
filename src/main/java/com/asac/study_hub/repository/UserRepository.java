package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Status;
import com.asac.study_hub.domain.User;
import com.asac.study_hub.exception.CustomException;
import com.asac.study_hub.exception.ExceptionType;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRepository implements IRepository {

    User user;

    private static final HashMap<Integer, User> users;
    private static final HashMap<String, User> sessionStorage;

    static {
        users = new HashMap<>();
        users.put(1, new User(1, "김정현", "solee3020@gmail.com", "solee6810", Status.ACTIVE));
        users.put(2, new User(2, "김지연", "jykim9335@gmail.com", "asas5656", Status.ACTIVE));

        sessionStorage = new HashMap<>();
    }

    public User save(User user) {
        users.put(users.size() + 1, user);
        return user;
    }

    public String saveSession(String sessionId, User user) {
        sessionStorage.put(sessionId, user);

        return sessionId;
    }

    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    public List<User> findAll() {
        return users.values().stream().toList();
    }

    public void update(User user) {

    }

    //private String searchSessionId(){}


    public User searchMyProfile(String sessionId) {
        return sessionStorage.get(sessionId);
    }


    public void deleteUser(User user) {
//        User user = findByUserId(id);
        user.delete();
    }

    @Override
    public void delete(User user) {

    }


    public User findByUserId(Integer id) {
        User findUser = users.values().stream()
            .filter((user) -> id.equals(user.getId()))
            .findAny()
            .orElseThrow(() -> new CustomException(ExceptionType.NOT_FOUNT_USER_BY_ID, id));
        return findUser;
    }

    public void updateUser(User user, User newUser) {
        user.update(newUser);
    }


    public User findByEmail(String email) {
        User findUser = users.values().stream()
            .filter((user) -> email.equals(user.getEmail()))
            .findAny().orElseThrow(() -> new CustomException(
                ExceptionType.FAILD_SIGNIN)); //백엔드 개발자에게는 어떤 정보가 틀렸는지 알려줘야함. 클라이언트에게는 그냥 로그인 실패로 띄어주고
        return findUser;
    }
}
