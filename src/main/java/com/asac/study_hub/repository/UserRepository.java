package com.asac.study_hub.repository;

import com.asac.study_hub.domain.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRepository implements IRepository {
    User user;

    public User save(User user) {
        return null;
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
