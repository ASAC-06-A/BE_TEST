package com.asac.study_hub.repository.dbRepository;

import com.asac.study_hub.domain.Study;
import com.asac.study_hub.repository.IRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudyRepository implements IRepository<Integer, Study> {

    @Override
    public Study save(Study entity) {
        return null;
    }

    @Override
    public Optional<Study> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Study> find(Study entity) {
        return Optional.empty();
    }

    @Override
    public List<Study> findAll() {
        return List.of();
    }

    @Override
    public void update(Study entity) {

    }

    @Override
    public void delete(Study entity) {

    }
}
