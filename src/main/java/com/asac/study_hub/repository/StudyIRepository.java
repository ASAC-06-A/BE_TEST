package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Study;
import com.asac.study_hub.domain.User;

import java.util.List;
import java.util.Optional;

public interface StudyIRepository {

    List<Study> findAll();
    Optional<Study> findById(Integer id);
    Integer save(Study study);
    Integer getId();
    List<Study> findByCategory(String category);
    void update(Study study, Study newstudy);
    void deleteAll(List<Study> study);
    Optional<Study> findByIdAndUser(Integer id, User user);
}

