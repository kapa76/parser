package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationRepository extends HibernateRepositoryBase implements AbstractRepository<Education> {
    @Override
    public Education findOne(Long id) {
        return getHibernateTemplate().get(Education.class, id);
    }

    @Override
    public Education findOne(String name) {
        return null;
    }

    @Override
    public List<Education> findAll() {
        return null;
    }

    @Override
    public void create(Education entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Education update(Education entity) {
        return null;
    }

    @Override
    public void delete(Education entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}