package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Study;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyIRepository {

    List<Study> findAll();

}
