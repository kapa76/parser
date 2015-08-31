package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenderResumeRepository extends HibernateRepositoryBase implements AbstractRepository<GenderResume> {
    @Override
    public GenderResume findOne(Long id) {
        return getHibernateTemplate().get(GenderResume.class, id);
    }

    @Override
    public GenderResume findOne(String name) {
        return null;
    }

    @Override
    public List<GenderResume> findAll() {
        return null;
    }

    @Override
    public void create(GenderResume entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public GenderResume update(GenderResume entity) {
        return null;
    }

    @Override
    public void delete(GenderResume entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}