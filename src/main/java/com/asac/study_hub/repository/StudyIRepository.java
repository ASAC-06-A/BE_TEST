package com.asac.study_hub.repository;

import com.asac.study_hub.domain.Study;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyIRepository {

    List<Study> findAll();
    Study save(Study entity);
    void delete(Integer id);
    Study update(Integer id, Study entity);
    Study findbyId (Integer id);

}
