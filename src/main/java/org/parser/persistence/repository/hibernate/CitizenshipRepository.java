package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CitizenshipRepository extends HibernateRepositoryBase implements AbstractRepository<Citizenship> {
    @Override
    public Citizenship findOne(Long id) {
        return getHibernateTemplate().get(Citizenship.class, id);
    }

    @Override
    public Citizenship findOne(String name) {
        return null;
    }

    @Override
    public List<Citizenship> findAll() {
        return null;
    }

    @Override
    public void create(Citizenship entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Citizenship update(Citizenship entity) {
        return null;
    }

    @Override
    public void delete(Citizenship entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}