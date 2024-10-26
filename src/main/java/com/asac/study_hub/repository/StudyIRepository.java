package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Study;

import java.util.List;
import java.util.Optional;

public interface StudyIRepository {

    List<Study> findAll();
    Optional<Study> findById(Integer id);
    Integer save(Study study);
    Integer getId();
    List<Study> findByCategory(String category);
}

