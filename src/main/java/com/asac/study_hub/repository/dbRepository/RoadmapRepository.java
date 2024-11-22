package com.asac.study_hub.repository.dbRepository;

import com.asac.study_hub.domain.Roadmap;
import com.asac.study_hub.repository.IRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoadmapRepository implements IRepository<Integer, Roadmap>  {

    @Override
    public Roadmap save(Roadmap entity) {
        return null;
    }

    @Override
    public Optional<Roadmap> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Roadmap> find(Roadmap entity) {
        return Optional.empty();
    }

    @Override
    public List<Roadmap> findAll() {
        return List.of();
    }

    @Override
    public void update(Roadmap entity) {

    }

    @Override
    public void delete(Roadmap entity) {

    }
}
