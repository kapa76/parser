package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Queuev;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueuevRepository extends AbstractRepo {

    public Queuev findOne(Long id) {
        return (Queuev) sessionFactory.getCurrentSession().get(Queuev.class, id);
    }


    public Queuev findOne(String name) {
        return null;
    }


    public List<Queuev> findAll() {
        return null;
    }


    public void create(Queuev entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public Queuev update(Queuev entity) {
        return null;
    }


    public void delete(Queuev entity) {

    }


    public void deleteById(long entityId) {

    }
}