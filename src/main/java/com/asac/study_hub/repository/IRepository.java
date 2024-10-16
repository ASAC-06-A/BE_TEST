package com.asac.study_hub.repository;

import com.asac.study_hub.domain.User;

import java.util.List;
import java.util.Optional;

public interface IRepository {

    User save(User user);
    Optional<User> findById(Integer id);
    List<User> findAll();
    void update(User user);
    void delete(User user);
}
