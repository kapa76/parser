package org.parser.persistence.repository;


import org.parser.persistence.model.City;

import java.util.List;

public interface AbstractRepository<T>{
    T findOne(Long id);
    T findOne(String name);
    List<T> findAll();
    void create(T entity);
    T update(T entity);
    void delete(T entity);
    void deleteById(long entityId);
}
