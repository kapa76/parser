package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceRepository extends HibernateRepositoryBase implements AbstractRepository<Place> {
    @Override
    public Place findOne(Long id) {
        return getHibernateTemplate().get(Place.class, id);
    }

    @Override
    public Place findOne(String name) {
        return null;
    }

    @Override
    public List<Place> findAll() {
        return null;
    }

    @Override
    public void create(Place entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Place update(Place entity) {
        return null;
    }

    @Override
    public void delete(Place entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}