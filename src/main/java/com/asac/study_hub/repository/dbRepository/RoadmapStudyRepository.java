package com.asac.study_hub.repository.dbRepository;

import com.asac.study_hub.domain.RoadmapStudy;
import com.asac.study_hub.repository.IRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoadmapStudyRepository implements IRepository<Integer, RoadmapStudy>  {

    @Override
    public RoadmapStudy save(RoadmapStudy entity) {
        return null;
    }

    @Override
    public Optional<RoadmapStudy> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<RoadmapStudy> find(RoadmapStudy entity) {
        return Optional.empty();
    }

    @Override
    public List<RoadmapStudy> findAll() {
        return List.of();
    }

    @Override
    public void update(RoadmapStudy entity) {

    }

    @Override
    public void delete(RoadmapStudy entity) {

    }
}
