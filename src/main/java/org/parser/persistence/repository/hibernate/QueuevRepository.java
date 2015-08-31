package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueuevRepository extends HibernateRepositoryBase implements AbstractRepository<Queuev> {
    @Override
    public Queuev findOne(Long id) {
        return getHibernateTemplate().get(Queuev.class, id);
    }

    @Override
    public Queuev findOne(String name) {
        return null;
    }

    @Override
    public List<Queuev> findAll() {
        return null;
    }

    @Override
    public void create(Queuev entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Queuev update(Queuev entity) {
        return null;
    }

    @Override
    public void delete(Queuev entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}