package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Study;
import org.springframework.stereotype.Repository;
import com.asac.study_hub.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyIRepository {

    List<Study> findAll(User user);
    Optional<Study> findById(Integer id);
    Integer save(Study study);
    Integer getId();
    List<Study> findByCategory(String category);
    void update(Study study, Study newstudy);
    void deleteAll(List<Study> study);
    Optional<Study> findByIdAndUser(Integer id, User user);
    Integer getStudyId();
    void delete(Study study);
}
