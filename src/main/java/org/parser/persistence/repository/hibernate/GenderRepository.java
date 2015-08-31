package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenderRepository extends HibernateRepositoryBase implements AbstractRepository<Gender> {
    @Override
    public Gender findOne(Long id) {
        return getHibernateTemplate().get(Gender.class, id);
    }

    @Override
    public Gender findOne(String name) {
        return null;
    }

    @Override
    public List<Gender> findAll() {
        return null;
    }

    @Override
    public void create(Gender entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Gender update(Gender entity) {
        return null;
    }

    @Override
    public void delete(Gender entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}