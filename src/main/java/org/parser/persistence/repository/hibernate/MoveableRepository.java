package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.parser.persistence.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoveableRepository extends HibernateRepositoryBase implements AbstractRepository<Moveable> {
    @Override
    public Moveable findOne(Long id) {
        return getHibernateTemplate().get(Moveable.class, id);
    }

    @Override
    public Moveable findOne(String name) {
        return null;
    }

    @Override
    public List<Moveable> findAll() {
        return null;
    }

    @Override
    public void create(Moveable entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public Moveable update(Moveable entity) {
        return null;
    }

    @Override
    public void delete(Moveable entity) {

    }

    @Override
    public void deleteById(long entityId) {

    }
}