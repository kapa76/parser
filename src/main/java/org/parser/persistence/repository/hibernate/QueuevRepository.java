package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueuevRepository extends AbstractRepo {

    public Queuev findOne(Long id) {
        return (Queuev) sessionFactory.openSession().get(Queuev.class, id);
    }


    public Queuev findOne(String name) {
        return null;
    }


    public List<Queuev> findAll() {
        return null;
    }


    public void create(Queuev entity) {
        sessionFactory.openSession().save(entity);
    }


    public Queuev update(Queuev entity) {
        return null;
    }


    public void delete(Queuev entity) {

    }


    public void deleteById(long entityId) {

    }
}