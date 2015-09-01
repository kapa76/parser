package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoveableRepository extends AbstractRepo {

    public Moveable findOne(Long id) {
        return (Moveable) sessionFactory.openSession().get(Moveable.class, id);
    }


    public Moveable findOne(String name) {
        return null;
    }


    public List<Moveable> findAll() {
        return null;
    }


    public void create(Moveable entity) {
        sessionFactory.openSession().save(entity);
    }


    public Moveable update(Moveable entity) {
        return null;
    }


    public void delete(Moveable entity) {

    }


    public void deleteById(long entityId) {

    }
}