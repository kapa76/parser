package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaritalStatusResumeRepository extends HibernateRepositoryBase implements AbstractRepository<MaritalStatusResume> {
    @Override
    public MaritalStatusResume findOne(Long id) {
        return getHibernateTemplate().get(MaritalStatusResume.class, id);
    }

    @Override
    public MaritalStatusResume findOne(String name) {
        return null;
    }

    @Override
    public List<MaritalStatusResume> findAll() {
        return null;
    }

    @Override
    public void create(MaritalStatusResume entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public MaritalStatusResume update(MaritalStatusResume entity) {
        return null;
    }

    @Override
    public void delete(MaritalStatusResume entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}