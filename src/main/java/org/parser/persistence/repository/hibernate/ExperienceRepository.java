package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExperienceRepository extends HibernateRepositoryBase implements AbstractRepository<Experience> {
    @Override
    public Experience findOne(Long id) {
        return getHibernateTemplate().get(Experience.class, id);
    }

    @Override
    public Experience findOne(String name) {
        return null;
    }

    @Override
    public List<Experience> findAll() {
        return null;
    }

    @Override
    public void create(Experience entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Experience update(Experience entity) {
        return null;
    }

    @Override
    public void delete(Experience entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}