package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceDetailRepository extends HibernateRepositoryBase implements AbstractRepository<PlaceDetail> {
    @Override
    public PlaceDetail findOne(Long id) {
        return getHibernateTemplate().get(PlaceDetail.class, id);
    }

    @Override
    public PlaceDetail findOne(String name) {
        return null;
    }

    @Override
    public List<PlaceDetail> findAll() {
        return null;
    }

    @Override
    public void create(PlaceDetail entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public PlaceDetail update(PlaceDetail entity) {
        return null;
    }

    @Override
    public void delete(PlaceDetail entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}