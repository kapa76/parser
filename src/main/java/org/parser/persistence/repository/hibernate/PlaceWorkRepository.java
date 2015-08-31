package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceWorkRepository extends HibernateRepositoryBase implements AbstractRepository<PlaceWork> {
    @Override
    public PlaceWork findOne(Long id) {
        return getHibernateTemplate().get(PlaceWork.class, id);
    }

    @Override
    public PlaceWork findOne(String name) {
        return null;
    }

    @Override
    public List<PlaceWork> findAll() {
        return null;
    }

    @Override
    public void create(PlaceWork entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public PlaceWork update(PlaceWork entity) {
        return null;
    }

    @Override
    public void delete(PlaceWork entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}