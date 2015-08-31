package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueuerRepository extends HibernateRepositoryBase implements AbstractRepository<Queuer> {
    @Override
    public Queuer findOne(Long id) {
        return getHibernateTemplate().get(Queuer.class, id);
    }

    @Override
    public Queuer findOne(String name) {
        return null;
    }

    @Override
    public List<Queuer> findAll() {
        return null;
    }

    @Override
    public void create(Queuer entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Queuer update(Queuer entity) {
        return null;
    }

    @Override
    public void delete(Queuer entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}