package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueuerRepository extends AbstractRepo {

    public Queuer findOne(Long id) {
        return (Queuer) sessionFactory.openSession().get(Queuer.class, id);
    }


    public Queuer findOne(String name) {
        return null;
    }


    public List<Queuer> findAll() {
        return null;
    }


    public void create(Queuer entity) {
        sessionFactory.openSession().save(entity);
    }


    public Queuer update(Queuer entity) {
        return null;
    }


    public void delete(Queuer entity) {

    }


    public void deleteById(long entityId) {

    }
}