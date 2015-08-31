package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessionalRepository extends HibernateRepositoryBase implements AbstractRepository<Professional> {
    @Override
    public Professional findOne(Long id) {
        return getHibernateTemplate().get(Professional.class, id);
    }

    @Override
    public Professional findOne(String name) {
        return null;
    }

    @Override
    public List<Professional> findAll() {
        return null;
    }

    @Override
    public void create(Professional entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Professional update(Professional entity) {
        return null;
    }

    @Override
    public void delete(Professional entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}