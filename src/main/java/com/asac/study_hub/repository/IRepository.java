package com.asac.study_hub.repository;

import jakarta.persistence.Entity;

import java.util.List;
import java.util.Optional;

public interface IRepository<I, E> {

    E save(E entity);
    Optional<E> findById(I id);
    Optional<E> find(E entity);
    List<E> findAll();
    void update(E entity);
    void delete(E entity);
    default Optional<E> findByEntity(Entity o) {
        return null;
    }

    default Optional<E> findByIdAndEntity(Entity o) {
        return null;
    }
}
